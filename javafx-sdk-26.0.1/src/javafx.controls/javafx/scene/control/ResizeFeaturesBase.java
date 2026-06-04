/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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

import javafx.beans.NamedArg;

/**
 * A wrapper class for use by the column resize policies offered by
 * controls such as {@link TableView} and {@link TreeTableView}.
 *
 * @param <S> the type of the objects contained within the control's items list
 * @since JavaFX 8.0
 */
public abstract class ResizeFeaturesBase<S> {
  private final TableColumnBase<S,?> column;
  private final Double delta;

  /**
   * Creates an instance of this class, with the provided TableColumnBase and
   * delta values being set and stored in this immutable instance.
   *
   * @param column The column upon which the resize is occurring, or null
   *      if this ResizeFeatures instance is being created as a result of a
   *      resize operation.
   * @param delta The amount of horizontal space added or removed in the
   *      resize operation.
   */
  public ResizeFeaturesBase(@NamedArg("column") TableColumnBase<S,?> column, @NamedArg("delta") Double delta) {
      this.column = column;
      this.delta = delta;
  }

  /**
   * Returns the width of the area available for columns.
   *
   * @return the width available for columns
   *
   * @since 20
   */
  public abstract double getContentWidth();

  /**
   * Returns the associated TreeView or TreeTableView control.
   *
   * @return the control in which the resize is occurring
   *
   * @since 20
   */
  public abstract Control getTableControl();

  /**
   * Returns the column upon which the resize is occurring, or null
   * if this ResizeFeatures instance was created as a result of a
   * resize operation.
   * @return the column upon which the resize is occurring
   */
  public TableColumnBase<S,?> getColumn() { return column; }

  /**
   * Returns the amount of horizontal space added or removed in the
   * resize operation.
   * @return the amount of horizontal space added or removed in the
   * resize operation
   */
  public Double getDelta() { return delta; }

  /**
   * Sets the column width during the resizing pass.
   *
   * @param col column being changed
   * @param width desired column width
   *
   * @since 20
   */
  public void setColumnWidth(TableColumnBase<S, ?> col, double width) {
      Control c = getTableControl();
      if (c.isSnapToPixel()) {
          double min = c.snapSizeX(col.getMinWidth());
          double max = c.snapSizeX(col.getMaxWidth());
          if (width < min) {
              width = min;
          } else if (width > max) {
              width = max;
              if (width < min) {
                  // safety check in case max < min
                  width = min;
              }
          } else {
              width = c.snapPositionX(width);
          }
          col.setWidth(width);
      } else {
          col.doSetWidth(width);
      }
  }
}
