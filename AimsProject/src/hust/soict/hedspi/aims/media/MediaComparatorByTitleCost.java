package hust.soict.hedspi.aims.media;

import java.util.*;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        if (m1.getTitle().compareTo(m2.getTitle()) > 0) {
            return 1;
        } else if (m1.getTitle().compareTo(m2.getTitle()) < 0) {
            return -1;
        } else {
            if (m1.getCost() > m2.getCost()) {
                return 1;
            } else if (m1.getCost() < m2.getCost()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
