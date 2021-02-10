package robotrace;

/**
* Materials that can be used for the robots.
*/
public enum Material {

    /** 
     * Gold material properties.
     * Modify the default values to make it look like gold.
     */
    GOLD (
            
        new float[] {0.751f, 0.606f, 0.226f, 1f},
        new float[] {0.628f, 0.555f, 0.366f, 1f},
        80.2f
    ),

    /**
     * Silver material properties.
     * Modify the default values to make it look like silver.
     */
    SILVER (
            
        new float[] {0.507f, 0.507f, 0.507f, 1f},
        new float[] {0.508f, 0.508f, 0.508f, 1f},
        51.2f
    ),

    /** 
     * Orange material properties.
     * Modify the default values to make it look like orange.
     */
    ORANGE (
            
        new float[] {0.703f, 0.270f, 0.0828f, 1f},
        new float[] {0.256f, 0.137f, 0.0860f, 1f},
        12.8f
    ),

    /**
     * Wood material properties.
     * Modify the default values to make it look like Wood.
     */
    WOOD (

        new float[] {0.524f, 0.328f, 0.113f, 1f},
        new float[] {0f, 0.214f, 0f, 1f},
        51.2f   

    );

    /** The diffuse RGBA reflectance of the material. */
    float[] diffuse;

    /** The specular RGBA reflectance of the material. */
    float[] specular;
    
    /** The specular exponent of the material. */
    float shininess;

    /**
     * Constructs a new material with diffuse and specular properties.
     */
    private Material(float[] diffuse, float[] specular, float shininess) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }
}
