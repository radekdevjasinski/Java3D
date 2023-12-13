package com.mygdx.game;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

public class Character extends DrawableObject {

    private final CharacterModel characterModel;

    public Character(CharacterModel characterModel) {
        super();
        this.characterModel = characterModel;
    }

    @Override
    public void draw(ModelBatch modelBatch) {
        // Sprawdź, czy model postaci istnieje
        if (characterModel != null) {
            // Ustaw pozycję, obrót, skalę postaci
            characterModel.transform.setToTranslation(position);
            characterModel.transform.rotate(Vector3.Y, rotation);
            characterModel.transform.scale(scale, scale, scale);

            // Dodaj model postaci do modelBatch, aby go narysować
            modelBatch.render(characterModel, environment);
        }
    }
}