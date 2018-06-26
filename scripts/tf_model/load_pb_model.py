import tensorflow as tf

# DO SESSION STUFF
sess = tf.Session()
# sess.run(tf.global_variables_initializer())

# Load THE MODEL
model_dir = "./output/tmp/model"

tf.saved_model.loader.load(sess, ["serve"], model_dir)

x = sess.graph.get_tensor_by_name('x:0')
y = sess.graph.get_tensor_by_name('y:0')

x_ = 2.0
y_ = sess.run(y, feed_dict={x: x_})

print("res={}".format(y_))
