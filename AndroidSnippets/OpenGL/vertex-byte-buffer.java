// definition
ByteBuffer buffer = ByteBuffer.allocateDirect(NUMBER_OF_BYTES);
buffer.order(ByteOrder.nativeOrder());
FloatBuffer floatBuffer = buffer.asFloatBuffer();

// put vertices in
float[] vertices = { /*... definitions of vertex positions etc. ...*/ };
floatBuffer.clear();
floatBuffer.put(vertices);
floatBuffer.flip(); //set position to 0 and limit to last position

// example
ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * 2 * 4); // 3 verteces, 2 coordinates, 4 bytes per float
byteBuffer.order(ByteOrder.nativeOrder());
FloatBuffer vertices = byteBuffer.asFloatBuffer();
vertices.put(new float[] { 	0.0f, 0.0f, 			// z-coordinate is set to z by OpenGL
							319.0f, 0.0f,
							160.0f, 479.0f });
vertices.flip();