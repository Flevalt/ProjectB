package projectS.model.Map;

import projectS.ArtemisLibrary.Component;

/**
 * 
 * NormalMap ist die begehbare, rangezoomte Map.
 * 
 *
 */

public class NormalMap extends Component {
 public int[][] map;
 public int width, height;
  
 public NormalMap() {
  map = new int[][] {
    { 0, 1, 2, 3, 4, 5, 6, 7, 8 },
    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    { 2, 2, 2, 2, 2, 2, 2, 2, 2 },
    { 2, 2, 2, 2, 2, 2, 2, 2, 2 },
    { 3, 3, 3, 3, 3, 3, 3, 3, 3 },
    { 3, 3, 3, 3, 3, 3, 3, 3, 3 }
  };
  width = map.length;
  height = map[0].length;
   
 }
}
