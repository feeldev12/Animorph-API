package me.feeldev.animorph.common.models;

public record ModelHitbox(Vector3 position, Vector2 size, float eyeHeight) {

    public static ModelHitbox empty() {
        return new ModelHitbox(new Vector3(-4.75, 0, -4.75), new Vector2(9.6f, 28.8f), 25.92f);
    }

    public ModelHitbox multiply(double scale) {
        return new ModelHitbox(position().multiply(scale), size().multiply(scale), (float) (eyeHeight() * scale));
    }

    public ModelHitbox divide(double scale) {
        return new ModelHitbox(position().divide(scale), size().divide(scale), (float) (eyeHeight() / scale));
    }

    public record Vector3(double x, double y, double z) {

        public Vector3() {
            this(0, 0, 0);
        }

        public Vector3 multiply(double scale) {
            return new Vector3(x * scale, y * scale, z * scale);
        }

        public Vector3 divide(double scale) {
            return new Vector3(x / scale, y / scale, z / scale);
        }
    }

    public record Vector2(double x, double y) {
        public Vector2 multiply(double scale) {
            return new Vector2(x * scale, y * scale);
        }

        public Vector2 divide(double scale) {
            return new Vector2(x / scale, y / scale);
        }
    }
}
