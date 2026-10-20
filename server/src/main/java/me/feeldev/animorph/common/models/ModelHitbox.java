package me.feeldev.animorph.common.models;

public record ModelHitbox(Vector3 position, Vector2 size, float eyeHeight) {

    public static ModelHitbox empty() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public ModelHitbox multiply(double scale) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public ModelHitbox divide(double scale) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public record Vector3(double x, double y, double z) {

        public Vector3() {
            this(0, 0, 0);
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Vector3 multiply(double scale) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Vector3 divide(double scale) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }

    public record Vector2(double x, double y) {

        public Vector2 multiply(double scale) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public Vector2 divide(double scale) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }
}
