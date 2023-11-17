package org.yl.auction.model;

public enum Condition {
        NEW("New"), USED("Used");
        private final String displayValue;

        private Condition(String displayValue) {
                this.displayValue = displayValue;
        }

        public String getDisplayValue() {
                return displayValue;
        }
}
