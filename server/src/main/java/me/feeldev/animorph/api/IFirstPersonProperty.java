package me.feeldev.animorph.api;

/**
 * Represents the first-person rendering configuration for a player's model.
 * <p>
 * Controls visibility and behavior of the model, custom arms, and equipment
 * when the player views themselves in first person.
 * <p>
 * Instances are mutable — you can modify them directly after obtaining via
 * {@link me.feeldev.animorph.api.IMorphAPI#getFirstPersonProperty(Object)} or
 * {@code me.feeldev.animorph.client.api.ClientMorphAPI#getFirstPersonProperty(UUID)}.
 * <p>
 * Use the {@link Builder} to create new instances:
 * <pre>{@code
 * IFirstPersonProperty property = IFirstPersonProperty.builder()
 *     .showModel(true)
 *     .modelOffset(0, 1.5, 0)
 *     .customArmsShow(true)
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

    ICustomArms customArms();

    boolean showEquipment();

    void setShowEquipment(boolean showEquipment);

    /**
     * Creates a new builder with default values (all false, offset 0,0,0).
     */
    static Builder builder() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Creates a new builder initialized from an existing property.
     *
     * @param source the property to copy values from
     */
    static Builder builder(IFirstPersonProperty source) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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

        public Builder showModel(boolean showModel) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Builder modelOffset(double x, double y, double z) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Builder customArmsShow(boolean show) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Builder customArmsBothHands(boolean bothHands) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Builder customArmsRenderItems(boolean customRenderItems) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Builder showEquipment(boolean showEquipment) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Builder from(IFirstPersonProperty source) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public IFirstPersonProperty build() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }

    class Default implements IFirstPersonProperty {

        public Default(boolean showModel, DefaultModelOptions modelOptions, DefaultCustomArms customArms, boolean showEquipment) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public boolean showModel() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setShowModel(boolean showModel) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public IModelOptions modelOptions() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public ICustomArms customArms() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public boolean showEquipment() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setShowEquipment(boolean showEquipment) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }

    class DefaultModelOptions implements IModelOptions {

        public DefaultModelOptions(double offsetX, double offsetY, double offsetZ) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public double offsetX() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public double offsetY() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public double offsetZ() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setOffsetX(double x) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setOffsetY(double y) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setOffsetZ(double z) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }

    class DefaultCustomArms implements ICustomArms {

        public DefaultCustomArms(boolean show, boolean bothHands, boolean customRenderItems) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public boolean show() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public boolean bothHands() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public boolean customRenderItems() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setShow(boolean show) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setBothHands(boolean bothHands) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        @Override
        public void setCustomRenderItems(boolean customRenderItems) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }
}
