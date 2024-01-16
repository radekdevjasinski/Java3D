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
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


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
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0, 0, 0);
		camera.near = 1f;
		camera.far = 300f;



		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 1f));
		//environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		lightPosition = new Vector3(0, 0, 0);
		pointLight = new PointLight();
		pointLight.set(Color.WHITE, lightPosition, 50f);
		environment.add(pointLight);

		objects = new Array<>();
		objects.add(new PrimitiveObject(createCubeModel(), Color.RED));
		objects.add(new PrimitiveObject(createSphereModel(), Color.GREEN));

		objects.get(0).getModelInstance().transform.set(new Vector3(-3,0,3), new Quaternion());
		objects.get(1).getModelInstance().transform.set(new Vector3(3,0,-3), new Quaternion());

	}

	private Model createCubeModel() {
		ModelBuilder modelBuilder = new ModelBuilder();
		return modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);
	}

	private Model createSphereModel() {
		ModelBuilder modelBuilder = new ModelBuilder();
		return modelBuilder.createSphere(7f, 7f, 7f, 20, 20, new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);
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

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			lightPosition.add(0, 0, 1 * delta * lightSpeed);
			lightPosition.y = 0;
			lightPosition.x = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			lightPosition.add(0, 0, -1 * delta * lightSpeed);
			lightPosition.y = 0;
			lightPosition.x = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			lightPosition.add(-1 * delta * lightSpeed, 0, 0);
			lightPosition.y = 0;
			lightPosition.z = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			lightPosition.add(1 * delta * lightSpeed, 0, 0);
			lightPosition.y = 0;
			lightPosition.z = 0;
		}

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
