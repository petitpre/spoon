from matplotlib.mlab import PCA

from matplotlib import pyplot as plt
import numpy as np
import sys

data = np.genfromtxt(sys.argv[1], delimiter=';')

mlab_pca = PCA(data)

plt.plot(mlab_pca.Y[0:10,0],mlab_pca.Y[0:10,1],
         'o', markersize=7, color='blue', alpha=0.5, label='reference')

plt.plot(mlab_pca.Y[10:20,0],mlab_pca.Y[10:20,1],
         'o', markersize=7, color='red', alpha=0.5, label='simple mutation')

plt.plot(mlab_pca.Y[20:30,0],mlab_pca.Y[20:30,1],
         'o', markersize=7, color='green', alpha=0.5, label='all mutations')

plt.legend()

plt.show()