package optical_character_recognition;

import java.util.*;

/**
 *
 * @author dombesz
 */
class Layer {

    Vector neurons;
    int size;

    public Layer(String l, int s) {
        String label;
        size = s;
        neurons = new Vector();
        for (int i = 0; i < s; i++) {
            label = new String(l) + String.valueOf(i);
            neurons.addElement(new Neuron(label));
        }
    }

    public Neuron getNeuron(int i) {
        int j = 0;
        boolean found = false;
        Neuron neuron = null;
        Enumeration e = neurons.elements();
        while (e.hasMoreElements()) {
            neuron = (Neuron) e.nextElement();
            if (i == j) {
                found = true;
                break;
            } else {
                j++;
            }
        }
        if (found == false) {
            neuron = null;
        }
        return neuron;
    }

    public void computeOutputs() {
        Neuron neuron;
        Enumeration e = neurons.elements();
        while (e.hasMoreElements()) {
            neuron = (Neuron) e.nextElement();
            neuron.computeOutput();
        }
    }

    public void computeBackpropDeltas(Vector s) // for output neurons
    {
        Neuron neuron;
        Enumeration e = neurons.elements();
        Enumeration d = s.elements();
        while (e.hasMoreElements()) {
            neuron = (Neuron) e.nextElement();
            neuron.computeBackpropDelta(((Double) d.nextElement()).doubleValue());
        }
    }

    public void computeBackpropDeltas() // for hidden neurons
    {
        Neuron neuron;
        Enumeration e = neurons.elements();
        while (e.hasMoreElements()) {
            neuron = (Neuron) e.nextElement();
            neuron.computeBackpropDelta();
        }
    }

    public void computeWeights() {
        Neuron neuron;
        Enumeration e = neurons.elements();
        while (e.hasMoreElements()) {
            neuron = (Neuron) e.nextElement();
            neuron.computeWeight();
        }
    }

    public void print() {
        Neuron neuron;
        Enumeration e = neurons.elements();
        while (e.hasMoreElements()) {
            neuron = (Neuron) e.nextElement();
            neuron.print();
        }
    }
}

