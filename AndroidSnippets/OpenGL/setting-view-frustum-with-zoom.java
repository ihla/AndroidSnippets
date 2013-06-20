/*
 * view port is centered around (x,y)
 * zoom < 1 - zooming in
 * zoom > 1 - zooming out
 */
gl.glOrthof(x – FRUSTUM_WIDTH / 2 * zoom, 
		x + FRUSTUM_WIDTH / 2 * zoom, 
		y – FRUSTUM_HEIGHT / 2 * zoom, 
		y + FRUSTUM_HEIGHT / 2 * zoom, 1, -1);