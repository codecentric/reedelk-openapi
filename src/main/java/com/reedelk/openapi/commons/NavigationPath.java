package com.reedelk.openapi.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavigationPath {

    private List<PathSegment> pathList;

    private NavigationPath() {
        pathList = new ArrayList<>();
    }

    private NavigationPath(List<PathSegment> pathList, String segmentKey, String segmentValue) {
        this.pathList = new ArrayList<>(pathList);
        this.pathList.add(new PathSegment(segmentKey, segmentValue));
    }

    public NavigationPath with(String segmentKey) {
        // Segment Key == Segment Value
        return new NavigationPath(pathList, segmentKey, segmentKey);
    }

    public NavigationPath with(String segmentKey, String segmentValue) {
        return new NavigationPath(pathList, segmentKey, segmentValue);
    }

    public static NavigationPath create() {
        return new NavigationPath();
    }

    @Override
    public String toString() {
        return pathList + "";
    }

    static class PathSegment {

        private final String segmentKey;
        private final String segmentValue;

        PathSegment(String segmentKey, String segmentValue) {
            this.segmentKey = segmentKey;
            this.segmentValue = segmentValue;
        }

        public String getSegmentKey() {
            return segmentKey;
        }

        public String getSegmentValue() {
            return segmentValue;
        }

        @Override
        public String toString() {
            if (Objects.equals(segmentKey, segmentValue)) {
                return segmentKey;
            } else {
                return segmentValue + " (" + segmentKey + ")";
            }
        }
    }
}
