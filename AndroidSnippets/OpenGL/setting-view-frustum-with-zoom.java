/*
 * view port is centered around (x,y)
 * x and y are our camera’s position in world space
 * zoom < 1 - zooming in
 * zoom > 1 - zooming out
 */
gl.glOrthof(x – FRUSTUM_WIDTH / 2 * zoom, 
		x + FRUSTUM_WIDTH / 2 * zoom, 
		y – FRUSTUM_HEIGHT / 2 * zoom, 
		y + FRUSTUM_HEIGHT / 2 * zoom, 1, -1);

/*
 * translation of touch coordinates into our world coordinate
 */
worldX = (touchX / Graphics.getWidth()) × FRUSTUM_WIDTH + x – FRUSTUM_WIDTH / 2;
worldY = (1 – touchY / Graphics.getHeight()) × FRUSTUM_HEIGHT + y – FRUSTUM_HEIGHT / 2;