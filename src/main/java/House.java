public class House {
    private double size;
    private boolean hasRoof;
    private boolean hasGarage;

    public House(double size, boolean hasRoof, boolean hasGarage) {
        this.size = size;
        this.hasRoof = hasRoof;
        this.hasGarage = hasGarage;
    }

    public double getSize() {
        return size;
    }

    public boolean isHasRoof() {
        return hasRoof;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public static class Builder {
        private double size;
        private boolean hasRoof;
        private boolean hasGarage;

        public Builder() {
        }

        ;

        public Builder withSize(double size) {
            this.size = size;
            return this;
        }

        public Builder withHasRoof(boolean hasRoof) {
            this.hasRoof = hasRoof;
            return this;
        }

        public Builder withHasGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        public House build() {
            return new House(size, hasRoof, hasGarage);
        }
    }


}
