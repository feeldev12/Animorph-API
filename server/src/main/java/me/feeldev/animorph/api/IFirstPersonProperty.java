package me.feeldev.animorph.api;

/**
 * Represents the first-person rendering configuration for a player's model.
 * <p>
 * Controls visibility and behavior of the model, custom arms, and equipment
 * when the player views themselves in first person.
 * <p>
 * Instances are mutable — you can modify them directly after obtaining via
 * {@link IMorphAPI#getFirstPersonProperty(Object)} or the client API.
 * <p>
 * Use the {@link Builder} to create new instances:
 * <pre>{@code
 * IFirstPersonProperty property = IFirstPersonProperty.builder()
 *     .showModel(true)
 *     .modelOffset(0, 1.5, 0)
 *     .showCustomArms(true)
 *     .customArmsBothHands(true)
 *     .customArmsRenderItems(true)
 *     .showEquipment(false)
 *     .build();
 *
 * api.updateFirstPersonProperty(player, property);
 * }</pre>
 */
public interface IFirstPersonProperty {

    boolean showModel();
    void setShowModel(boolean showModel);

    IModelOptions modelOptions();

    boolean showCustomArms();
    void setShowCustomArms(boolean showCustomArms);

    ICustomArms customArms();

    boolean showEquipment();
    void setShowEquipment(boolean showEquipment);

    /**
     * Creates a new builder with default values (all false, offset 0,0,0).
     */
    static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a new builder initialized from an existing property.
     *
     * @param source the property to copy values from
     */
    static Builder builder(IFirstPersonProperty source) {
        return new Builder().from(source);
    }

    /**
     * Model rendering options for first person view.
     */
    interface IModelOptions {
        double offsetX();
        double offsetY();
        double offsetZ();

        void setOffsetX(double x);
        void setOffsetY(double y);
        void setOffsetZ(double z);
    }

    /**
     * Custom arms rendering options.
     */
    interface ICustomArms {
        boolean show();
        boolean bothHands();
        boolean customRenderItems();

        void setShow(boolean show);
        void setBothHands(boolean bothHands);
        void setCustomRenderItems(boolean customRenderItems);
    }

    /**
     * Builder for creating {@link IFirstPersonProperty} instances.
     */
    class Builder {
        private boolean showModel;
        private double modelOffsetX;
        private double modelOffsetY;
        private double modelOffsetZ;
        private boolean showCustomArms;
        private boolean customArmsShow;
        private boolean customArmsBothHands;
        private boolean customArmsRenderItems;
        private boolean showEquipment;

        public Builder showModel(boolean showModel) {
            this.showModel = showModel;
            return this;
        }

        public Builder modelOffset(double x, double y, double z) {
            this.modelOffsetX = x;
            this.modelOffsetY = y;
            this.modelOffsetZ = z;
            return this;
        }

        public Builder showCustomArms(boolean showCustomArms) {
            this.showCustomArms = showCustomArms;
            return this;
        }

        public Builder customArmsShow(boolean show) {
            this.customArmsShow = show;
            return this;
        }

        public Builder customArmsBothHands(boolean bothHands) {
            this.customArmsBothHands = bothHands;
            return this;
        }

        public Builder customArmsRenderItems(boolean customRenderItems) {
            this.customArmsRenderItems = customRenderItems;
            return this;
        }

        public Builder showEquipment(boolean showEquipment) {
            this.showEquipment = showEquipment;
            return this;
        }

        public Builder from(IFirstPersonProperty source) {
            this.showModel = source.showModel();
            this.showEquipment = source.showEquipment();
            this.showCustomArms = source.showCustomArms();
            if (source.modelOptions() != null) {
                this.modelOffsetX = source.modelOptions().offsetX();
                this.modelOffsetY = source.modelOptions().offsetY();
                this.modelOffsetZ = source.modelOptions().offsetZ();
            }
            if (source.customArms() != null) {
                this.customArmsShow = source.customArms().show();
                this.customArmsBothHands = source.customArms().bothHands();
                this.customArmsRenderItems = source.customArms().customRenderItems();
            }
            return this;
        }

        public IFirstPersonProperty build() {
            return new Default(
                    showModel,
                    new DefaultModelOptions(modelOffsetX, modelOffsetY, modelOffsetZ),
                    showCustomArms,
                    new DefaultCustomArms(customArmsShow, customArmsBothHands, customArmsRenderItems),
                    showEquipment
            );
        }
    }

    class Default implements IFirstPersonProperty {
        private boolean showModel;
        private final DefaultModelOptions modelOptions;
        private boolean showCustomArms;
        private final DefaultCustomArms customArms;
        private boolean showEquipment;

        public Default(boolean showModel, DefaultModelOptions modelOptions, boolean showCustomArms, DefaultCustomArms customArms, boolean showEquipment) {
            this.showModel = showModel;
            this.modelOptions = modelOptions;
            this.showCustomArms = showCustomArms;
            this.customArms = customArms;
            this.showEquipment = showEquipment;
        }

        @Override public boolean showModel() { return showModel; }
        @Override public void setShowModel(boolean showModel) { this.showModel = showModel; }
        @Override public IModelOptions modelOptions() { return modelOptions; }
        @Override public boolean showCustomArms() { return showCustomArms; }
        @Override public void setShowCustomArms(boolean showCustomArms) { this.showCustomArms = showCustomArms; }
        @Override public ICustomArms customArms() { return customArms; }
        @Override public boolean showEquipment() { return showEquipment; }
        @Override public void setShowEquipment(boolean showEquipment) { this.showEquipment = showEquipment; }
    }

    class DefaultModelOptions implements IModelOptions {
        private double offsetX;
        private double offsetY;
        private double offsetZ;

        public DefaultModelOptions(double offsetX, double offsetY, double offsetZ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
        }

        @Override public double offsetX() { return offsetX; }
        @Override public double offsetY() { return offsetY; }
        @Override public double offsetZ() { return offsetZ; }
        @Override public void setOffsetX(double x) { this.offsetX = x; }
        @Override public void setOffsetY(double y) { this.offsetY = y; }
        @Override public void setOffsetZ(double z) { this.offsetZ = z; }
    }

    class DefaultCustomArms implements ICustomArms {
        private boolean show;
        private boolean bothHands;
        private boolean customRenderItems;

        public DefaultCustomArms(boolean show, boolean bothHands, boolean customRenderItems) {
            this.show = show;
            this.bothHands = bothHands;
            this.customRenderItems = customRenderItems;
        }

        @Override public boolean show() { return show; }
        @Override public boolean bothHands() { return bothHands; }
        @Override public boolean customRenderItems() { return customRenderItems; }
        @Override public void setShow(boolean show) { this.show = show; }
        @Override public void setBothHands(boolean bothHands) { this.bothHands = bothHands; }
        @Override public void setCustomRenderItems(boolean customRenderItems) { this.customRenderItems = customRenderItems; }
    }
}
