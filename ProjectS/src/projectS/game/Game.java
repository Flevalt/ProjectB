package projectS.game;
import engine.Engine;
import engine.graphics.VertexArray;
import engine.input.Input;
import engine.math.Vector3f;
import engine.objects.DisplayObject;
import engine.objects.GameObject;
import engine.objects.Scene;
import engine.textures.Texture;

public class Game extends Engine {
	
	GameObject character1;
	GameObject character2;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
	
	@Override
	public void update() {
		if(Input.keys[Input.KEY_LEFT]) {
			character1.changePosition(-0.05f, 0f, 0f);	
		}
		if(Input.keys[Input.KEY_RIGHT]) {
			character1.changePosition(0.05f, 0f, 0f);
		}
		if(Input.keys[Input.KEY_UP]) {
			character1.changePosition(0f, 0.05f, 0f);	
		}
		if(Input.keys[Input.KEY_DOWN]) {
			character1.changePosition(0f, -0.05f, 0f);	
		}
		
	}

	@Override
	public void init() {	
		
		//TEST------
		float[] vertices = new float[]{
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
		};
		byte[] index = new byte[] {
				0, 1, 3,
				3, 1, 2
		};
		
		float[] tex = new float[] {
				0f, 0f,
				0f, 1f,
				1f, 1f,
				1f, 0f
		};		
		
		VertexArray vertexArray = new VertexArray(vertices, index, tex);
		DisplayObject obj = new DisplayObject(new Texture("res/pictures/object1.png"), vertexArray);
		
		Scene scene = new Scene();
		character1 = new GameObject("Character1", obj);
		character2 = new GameObject("Character2", obj);
		character1.setScale(0.2f);
		character2.setScale(0.2f);
		character1.setPosition(new Vector3f(0f, 0f, -0.5f));
		scene.addDisplayObject(character1);
		scene.addDisplayObject(character2);
		setScene(scene);
	}


	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
}
