// definition
ByteBuffer buffer = ByteBuffer.allocateDirect(NUMBER_OF_BYTES);
buffer.order(ByteOrder.nativeOrder());
FloatBuffer floatBuffer = buffer.asFloatBuffer();

//put vertices in
float[] vertices = { /*... definitions of vertex positions etc. ...*/ };
floatBuffer.clear();
floatBuffer.put(vertices);
floatBuffer.flip(); //set position to 0 and limit to last position