package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class Engine extends ApplicationAdapter {
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private Environment environment;
	private Array<PrimitiveObject> objects;
	private PointLight pointLight;
	private Vector3 lightPosition;
	public final float rotationSpeed = 90f; // Prędkość rotacji w stopniach na sekundę
	public final float lightSpeed = 30f;

	@Override
	public void create() {
		camera = new PerspectiveCamera(100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0, 0, 0);
		camera.near = 1f;
		camera.far = 300f;

		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));

		lightPosition = new Vector3(0, 0, 0);
		pointLight = new PointLight();
		pointLight.set(Color.WHITE, lightPosition, 100f);
		environment.add(pointLight);

		Texture sphereTexture = new Texture(Gdx.files.internal("heh.png"));
		Texture cubeTexture = new Texture(Gdx.files.internal("toy.jpeg"));
		Texture coneTexture = new Texture(Gdx.files.internal("maxwell.png"));
		objects = new Array<>();
		objects.add(new PrimitiveObject(ModelCreator.createCubeModel(cubeTexture), cubeTexture));
		objects.add(new PrimitiveObject(ModelCreator.createSphereModel(sphereTexture), sphereTexture));
		Model modelJoin1 = ModelCreator.createConeModel(Color.BLUE);
		Model modelJoin2 = ModelCreator.createCylinderModel(Color.WHITE);
		List<Model> models = new ArrayList<>();
		models.add(modelJoin1); models.add(modelJoin2);

		objects.add(new PrimitiveObject(ModelCreator.createIrregularShapeModel(models),Color.BLUE));
		objects.add(new PrimitiveObject(ModelCreator.createPlaneModel(Color.GOLD), Color.GOLD));

		objects.add(new PrimitiveObject(ModelCreator.createConeModel(coneTexture), coneTexture));

		objects.get(4).getModelInstance().transform.set(new Vector3(-14,0,14), new Quaternion());
		objects.get(0).getModelInstance().transform.set(new Vector3(-7,0,7), new Quaternion());
		objects.get(1).getModelInstance().transform.set(new Vector3(0,0,0), new Quaternion());
		objects.get(2).getModelInstance().transform.set(new Vector3(7,0,-7), new Quaternion());
		objects.get(3).getModelInstance().transform.set(new Vector3(14,0,-14), new Quaternion());

	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();

		modelBatch.begin(camera);
		for (PrimitiveObject object : objects) {
			modelBatch.render(object.getModelInstance(), environment);
			handleInput(object);
		}
		modelBatch.end();
		handleLightInput();
	}

	private void handleLightInput() {
		float delta = Gdx.graphics.getDeltaTime();
		Vector3 lightMovement = new Vector3();

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			lightMovement.add(0, delta * lightSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			lightMovement.add(0, -delta * lightSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			lightMovement.add(-delta * lightSpeed, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			lightMovement.add(delta * lightSpeed, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			lightPosition.set(0, 0, 0);
			return;
		}

		lightPosition.add(lightMovement);
		pointLight.setPosition(lightPosition);
	}

	private void handleInput(PrimitiveObject object) {
		float delta = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			object.getModelInstance().transform.rotate(Vector3.Y, rotationSpeed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			object.getModelInstance().transform.rotate(Vector3.Y, -rotationSpeed * delta);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			object.getModelInstance().transform.rotate(Vector3.X, rotationSpeed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			object.getModelInstance().transform.rotate(Vector3.X, -rotationSpeed * delta);
		}
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}
}
