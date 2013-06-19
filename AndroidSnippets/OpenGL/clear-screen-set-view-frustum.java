import javax.microedition.khronos.opengles.GL10;

// clear screen
gl.glClearColor(0,0,0,1);
gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // set background color to black
/* set view port
 * OpenGL ES uses the viewport as a way to translate the coordinates of points projected to
 * the near clipping plane to framebuffer pixel coordinates.
 */
gl.glViewport(0, 0, getViewWidth(), getViewHeight()); // set view port to whole screen size
// set orthogonal projection
gl.glMatrixMode(GL10.GL_PROJECTION);
gl.glLoadIdentity();
gl.glOrthof(0, 320, 0, 480, 1, -1); // coordinate system now goes from (0,0,1) to (320,480,–1)
									// for portrait mode rendering
									//  y ^
									//    |
									//    +---> x
									// z /
