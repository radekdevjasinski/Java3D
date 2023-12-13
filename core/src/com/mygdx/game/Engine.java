package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Engine extends ApplicationAdapter {
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private ModelInstance modelInstance;
	private Environment environment;
	public final float rotationSpeed = 90f; // Prędkość rotacji w stopniach na sekundę

	@Override
	public void create() {
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0, 0, 0);
		camera.near = 1f;
		camera.far = 300f;

		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		// Stworzenie sześcianu
		modelInstance = new ModelInstance(createCubeModel());
	}

	private Model createCubeModel() {
		ModelBuilder modelBuilder = new ModelBuilder();
		return modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		handleInput(); // Dodane obsługi wejścia

		camera.update();

		modelBatch.begin(camera);
		modelBatch.render(modelInstance, environment);
		modelBatch.end();
	}

	private void handleInput() {
		float delta = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			modelInstance.transform.rotate(Vector3.Y, rotationSpeed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			modelInstance.transform.rotate(Vector3.Y, -rotationSpeed * delta);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			modelInstance.transform.rotate(Vector3.X, rotationSpeed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			modelInstance.transform.rotate(Vector3.X, -rotationSpeed * delta);
		}
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		// Dodaj ewentualne dodatkowe zasoby do zwolnienia
	}
}
