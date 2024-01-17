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
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
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
	public final float rotationSpeed = 90f; // Prędkość rotacji w stopniach na sekundę

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
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		objects = new Array<>();
		objects.add(new PrimitiveObject(createCubeModel(), Color.RED));
		objects.add(new PrimitiveObject(createSphereModel(), Color.GREEN));
		objects.add(new PrimitiveObject(createIrregularShapeModel(Color.GOLD), Color.GOLD));

		objects.get(0).getModelInstance().transform.set(new Vector3(-7,0,7), new Quaternion());
		objects.get(1).getModelInstance().transform.set(new Vector3(0,0,0), new Quaternion());
		objects.get(2).getModelInstance().transform.set(new Vector3(7,0,-7), new Quaternion());

	}

	private Model createCubeModel() {
		ModelBuilder modelBuilder = new ModelBuilder();
		return modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);
	}

	private Model createSphereModel() {
		ModelBuilder modelBuilder = new ModelBuilder();
		return modelBuilder.createSphere(7f, 7f, 7f, 20, 20, new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);
	}

	private Model createIrregularShapeModel(Color color) {
		ModelBuilder modelBuilder = new ModelBuilder();
		List<Model> models = new ArrayList<>();
/*
		// Tworzenie sześcianu
		modelBuilder.begin();
		MeshPartBuilder partBuilder = modelBuilder.part("box", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, new Material(ColorAttribute.createDiffuse(color)));
		partBuilder.box(4f, 4f, 4f);
		models.add(modelBuilder.end());
*/
		// Tworzenie stożka
		modelBuilder.begin();
		MeshPartBuilder partBuilder = modelBuilder.part("cone", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, new Material(ColorAttribute.createDiffuse(color)));
		partBuilder.cone(5f, 10f, 5f, 10);
		models.add(modelBuilder.end());

		models.add(createCubeModel());

		// Łączenie modeli
		Model finalModel = new Model();
		for(Model model : models)
		{
			finalModel.nodes.addAll(model.nodes);
			finalModel.meshes.addAll(model.meshes);
			finalModel.materials.addAll(model.materials);

		}
		return finalModel;
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
