/*
 * Copyright (c) 2013, 2024, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javafx.scene.control;

import com.sun.javafx.scene.control.skin.Utils;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;

class ControlUtils {
    private ControlUtils() { }

    public static void scrollToIndex(final Control control, int index) {
        Utils.executeOnceWhenPropertyIsNonNull(control.skinProperty(), (Skin<?> skin) -> {
            Event.fireEvent(control, new ScrollToEvent<>(control, control, ScrollToEvent.scrollToTopIndex(), index));
        });
    }

    public static void scrollToColumn(final Control control, final TableColumnBase<?, ?> column) {
        Utils.executeOnceWhenPropertyIsNonNull(control.skinProperty(), (Skin<?> skin) -> {
            control.fireEvent(new ScrollToEvent<TableColumnBase<?, ?>>(control, control, ScrollToEvent.scrollToColumn(), column));
        });
    }

    static boolean controlShouldRequestFocusIfCurrentFocusOwnerIsChild(Control c) {
        Scene scene = c.getScene();
        final Node focusOwner = scene == null ? null : scene.getFocusOwner();
        if (focusOwner == null) {
            return true;
        } else if (! c.equals(focusOwner)) {
            Parent p = focusOwner.getParent();
            while (p != null) {
                if (c.equals(p)) {
                    return true;
                }
                p = p.getParent();
            }
        }
        return false;
    }

    static void requestFocusOnControlOnlyIfCurrentFocusOwnerIsChild(Control c) {
        if (controlShouldRequestFocusIfCurrentFocusOwnerIsChild(c)) {
            c.requestFocus();
        }
    }

    static <T> ListChangeListener.Change<T> buildClearAndSelectChange(
            ObservableList<T> list, List<T> removed, T retainedRow, Comparator<T> rowComparator) {
        return new ListChangeListener.Change<T>(list) {
            private final int[] EMPTY_PERM = new int[0];

            private final int removedSize = removed.size();

            private final List<T> firstRemovedRange;
            private final List<T> secondRemovedRange;

            private boolean invalid = true;
            private boolean atFirstRange = true;

            private int from = -1;

            {
                int insertionPoint = Collections.binarySearch(removed, retainedRow, rowComparator);
                if (insertionPoint >= 0) {
                    firstRemovedRange = removed;
                    secondRemovedRange = Collections.emptyList();
                } else {
                    int midIndex = -insertionPoint - 1;
                    firstRemovedRange = removed.subList(0, midIndex);
                    secondRemovedRange = removed.subList(midIndex, removedSize);
                }
            }

            @Override public int getFrom() {
                checkState();
                return from;
            }

            @Override public int getTo() {
                return getFrom();
            }

            @Override public List<T> getRemoved() {
                checkState();
                return atFirstRange ? firstRemovedRange : secondRemovedRange;
            }

            @Override public int getRemovedSize() {
                checkState();
                return atFirstRange ? firstRemovedRange.size() : secondRemovedRange.size();
            }

            @Override protected int[] getPermutation() {
                checkState();
                return EMPTY_PERM;
            }

            @Override public boolean next() {
                if (invalid) {
                    invalid = false;

                    // point 'from' to the first position, relative to
                    // the underlying selectedCells index.
                    from = atFirstRange ? 0 : 1;
                    return true;
                }

                if (atFirstRange && !secondRemovedRange.isEmpty()) {
                    atFirstRange = false;

                    // point 'from' to the second position, relative to
                    // the underlying selectedCells index.
                    from = 1;
                    return true;
                }

                return false;
            }

            @Override public void reset() {
                invalid = true;
                atFirstRange = !firstRemovedRange.isEmpty();
            }

            private void checkState() {
                if (invalid) {
                    throw new IllegalStateException("Invalid Change state: next() must be called before inspecting the Change.");
                }
            }
        };
    }

    public static <S> void updateSelectedIndices(MultipleSelectionModelBase<S> sm, boolean isCellSelectionEnabled, ListChangeListener.Change<? extends TablePositionBase<?>> c, IntPredicate removeRowFilter) {
        sm.selectedIndices._beginChange();

        while (c.next()) {
            sm.startAtomic();

            final List<Integer> removed = new ArrayList<>(c.getRemovedSize());
            c.getRemoved().stream()
                    .mapToInt(TablePositionBase::getRow)
                    .distinct()
                    .filter(removeRowFilter)
                    .forEach(row -> {
                        removed.add(row);
                        sm.selectedIndices.clear(row);
                    });

            final int[] addedSize = new int[1];
            c.getAddedSubList().stream()
                    .mapToInt(TablePositionBase::getRow)
                    .distinct()
                    .forEach(row -> {
                        addedSize[0]++;
                        sm.selectedIndices.set(row);
                    });

            sm.stopAtomic();

            int from = c.getFrom();
            if (isCellSelectionEnabled && 0 < from && from < c.getList().size()) {
                // convert origin of change of list of tablePositions
                // into origin of change of list of rows
                int tpRow = c.getList().get(from).getRow();
                from = sm.selectedIndices.indexOf(tpRow);
            }
            final int to = from + addedSize[0];

            if (c.wasReplaced()) {
                sm.selectedIndices._nextReplace(from, to, removed);
            } else if (c.wasRemoved()) {
                sm.selectedIndices._nextRemove(from, removed);
            } else if (c.wasAdded()) {
                sm.selectedIndices._nextAdd(from, to);
            }
        }
        c.reset();
        sm.selectedIndices.reset();

        if (sm.isAtomic()) {
            return;
        }

        // Fix for JDK-8123234 - the selectedItems list was going to
        // empty, but the selectedItem property was staying non-null.
        // There is a unit test for this, so if a more elegant solution
        // can be found in the future and this code removed, the unit
        // test will fail if it isn't fixed elsewhere.
        // makeAtomic toggle added to resolve JDK-8117117
        if (sm.getSelectedItems().isEmpty() && sm.getSelectedItem() != null) {
            sm.setSelectedItem(null);
        }

        sm.selectedIndices._endChange();
    }

    public static <S> int getIndexOfChildWithDescendant(TreeItem<S> parent, TreeItem<S> item) {
        if (item == null || parent == null) {
            return -1;
        }
        TreeItem<S> child = item, ancestor = item.getParent();
        while (ancestor != null) {
            if (ancestor == parent) {
                return parent.getChildren().indexOf(child);
            }
            child = ancestor;
            ancestor = child.getParent();
        }
        return -1;
    }

    public static <S> boolean isTreeItemIncludingAncestorsExpanded(TreeItem<S> item) {
        if (item == null || !item.isExpanded()) {
            return false;
        }
        TreeItem<S> ancestor = item.getParent();
        while (ancestor != null) {
            if (!ancestor.isExpanded()) {
                return false;
            }
            ancestor = ancestor.getParent();
        }
        return true;
    }
}
