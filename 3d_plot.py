import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import numpy as np

def plot_3d_graph():
    # Create a new figure for 3D plotting
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')

    # Generate data for a helix
    theta = np.linspace(-4 * np.pi, 4 * np.pi, 100)
    z = np.linspace(-2, 2, 100)
    r = z**2 + 1
    x = r * np.sin(theta)
    y = r * np.cos(theta)

    # Plot the helix
    ax.plot(x, y, z, label='Helix Curve')
    ax.legend()

    # Set labels
    ax.set_xlabel('X Axis')
    ax.set_ylabel('Y Axis')
    ax.set_zlabel('Z Axis')

    # Show the plot
    plt.show()

if __name__ == "__main__":
    plot_3d_graph()