package projectS.game;
import engine.Engine;
import engine.display.Resolution;
import engine.graphics.Model;
import engine.input.Input;
import engine.math.Vector3f;
import engine.objects.TexturedModel;
import engine.objects.GameObject;
import engine.objects.Scene;
import engine.textures.Texture;

public class Game extends Engine {
	
	private GameObject character1;
	private GameObject character2;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.getDisplay().setResolution(Resolution.HD);
		//game.getDisplay().activateFullscreen();
		game.run();
	}
	
	@Override
	public void update() {
		if(Input.keys[Input.KEY_LEFT]) {
			character1.changePosition(-0.05f, 0f, 0f);	
			printPosition(character1);
		}
		if(Input.keys[Input.KEY_RIGHT]) {
			character1.changePosition(0.05f, 0f, 0f);
			printPosition(character1);
		}
		if(Input.keys[Input.KEY_UP]) {
			character1.changePosition(0f, 0.05f, 0f);
			printPosition(character1);
		}
		if(Input.keys[Input.KEY_DOWN]) {
			character1.changePosition(0f, -0.05f, 0f);	
			printPosition(character1);
		}
		if(Input.keys[Input.KEY_X]) {
			character1.changePosition(0f, 0f, -0.05f);	
			printPosition(character1);
		}
		if(Input.keys[Input.KEY_C]) {
			character1.changePosition(0f, 0f, 0.05f);	
			printPosition(character1);
		}
		if(Input.keys[Input.KEY_W]) {
			this.getCamera().changePosition(0.0f, 0.05f, 0f);
		}
		if(Input.keys[Input.KEY_S]) {
			this.getCamera().changePosition(0.0f, -0.05f, 0f);
		}
		if(Input.keys[Input.KEY_A]) {
			this.getCamera().changePosition(-0.05f, 0.0f, 0f);
		}
		if(Input.keys[Input.KEY_D]) {
			this.getCamera().changePosition(0.05f, 0.0f, 0f);
		}
		
	}

	private void printPosition(GameObject obj) {
		System.out.println(obj.getPosition().x + " : " + obj.getPosition().y + " : " + obj.getPosition().z);
	}
	
	@Override
	public void init() {	
		
		//TEST------
		float[] vertices = new float[]{
				-0.5f, 0.5f, -1f,
				-0.5f, -0.5f, -1f,
				0.5f, -0.5f, -1f,
				0.5f, 0.5f, -1f,
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
		
		Model vertexArray = new Model(vertices, index, tex);
		TexturedModel obj = new TexturedModel(new Texture("res/pictures/object1.png"), vertexArray);
		
		Scene scene = new Scene();
		character1 = new GameObject("Character1", obj);
		character2 = new GameObject("Character2", obj);
		//character1.setScale(0.2f);
		character2.setScale(0.2f);
		character1.setPosition(new Vector3f(0f, 0f, 0f));
		scene.addDisplayObject(character1);
		scene.addDisplayObject(character2);
		setScene(scene);
	}


	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
}
