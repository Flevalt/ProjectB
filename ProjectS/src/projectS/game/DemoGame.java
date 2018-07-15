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
import engine.utils.FileUtils;

public class DemoGame extends Engine {
	
	private GameObject character1;
	private GameObject character2;
	private GameObject character3;
	
	public static void main(String[] args) {
		DemoGame game = new DemoGame();
		game.getDisplay().setResolution(Resolution.HD); //Change the resolution
		//game.getDisplay().activateFullscreen();
		game.run();
	}
	
	@Override
	public void update() {
		/*
		 * Change the position of the object with arrow keys.
		 * Prints the position of the object.
		 */
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
		/*
		 * Change the camera with WASD
		 */
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
		if(Input.keys[Input.KEY_KP_ADD]) {
			this.getCamera().changePosition(0.0f, 0.0f, -0.05f);
		}	
		if(Input.keys[Input.KEY_KP_SUBTRACT]) {
			this.getCamera().changePosition(0.0f, 0.0f, 0.05f);
		}	
		if(Input.keys[Input.KEY_DELETE]) {
			character1.getTexture().delete(); //Delete textures of character 1 and 3.
		}	
	}

	private void printPosition(GameObject obj) {
		System.out.println(obj.getPosition().x + " : " + obj.getPosition().y + " : " + obj.getPosition().z);
	}
	
	@Override
	public void init() {	
			
		//---------------------------------------------------------------
		
		TexturedModel obj = FileUtils.loadSprite("res/pictures/object1.png");
		
		TexturedModel obj2 = FileUtils.loadSprite("res/pictures/character.jpg");
		
		Scene scene = new Scene();
		character1 = new GameObject("Character1", obj2);
		character3 = new GameObject("Character3", obj2);
		character2 = new GameObject("Character2", obj);
		character2.setScale(0.2f);
		character1.setPosition(new Vector3f(0f, 0f, 0f));
		character3.setPosition(new Vector3f(1f, 0f, 0f));
		scene.addDisplayObject(character1);
		scene.addDisplayObject(character2);
		scene.addDisplayObject(character3);
		setScene(scene);
	}


	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
}
