import tensorflow as tf
import os

tf.reset_default_graph()

W = tf.get_variable('w', initializer=tf.constant(2.0), dtype=tf.float32)
x = tf.placeholder(tf.float32, name='x')

# Model output y = W*x
y = tf.multiply(W, x, name='y')

# DO SESSION STUFF
sess = tf.Session()
sess.run(tf.global_variables_initializer())

# SAVE THE MODEL
model_dir = "./output/tmp/model"
os.system("rm -rf {}".format(model_dir))

builder = tf.saved_model.builder.SavedModelBuilder(model_dir)
builder.add_meta_graph_and_variables(
    sess,
    [tf.saved_model.tag_constants.SERVING]
)
builder.save()