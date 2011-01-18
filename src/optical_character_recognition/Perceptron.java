package optical_character_recognition;

import java.util.*;

/**
 *
 * @author dombesz
 */
public class Perceptron
{
  Vector           layers;
  Vector           inputSamples;
  Vector           outputSamples;
  public Layer     inputLayer;
  public Layer     outputLayer;
  public double    error;

  public Perceptron(int i,int o)
  {
    layers          = new Vector();
    inputSamples    = new Vector();
    outputSamples   = new Vector();
    inputLayer      = new Layer("I",i+1); // plus the bias
    outputLayer     = new Layer("O",o);
    layers.addElement(inputLayer);
    layers.addElement(outputLayer);
    error = 0.0;
  }
  public void addLayer(int n,String name)
  {
    layers.insertElementAt(new Layer(name,n),layers.size()-1);
  }
  public Layer getLayer(int i)
  {
    int         j=0;
    boolean     found=false;
    Layer       layer=null;
    Enumeration e = layers.elements();
    while(e.hasMoreElements())
    {
      layer = (Layer)e.nextElement();
      if (i==j)
      {
        found = true;
        break;
      } else j++;
    }
    if (found==false) layer = null;
    return layer;
  }
  public void connect(int sourceLayer,int sourceNeuron,
		      int destLayer,int destNeuron)
  {
    new Synapse(getLayer(sourceLayer).getNeuron(sourceNeuron),
                getLayer(destLayer).getNeuron(destNeuron));
  }
  public void biasConnect(int destLayer,int destNeuron)
  {
    new Synapse(inputLayer.getNeuron(inputLayer.size-1),
                getLayer(destLayer).getNeuron(destNeuron));
  }
  public void removeSamples()
  {
    inputSamples.removeAllElements();
    outputSamples.removeAllElements();
  }
  public void addSample(Vector i,Vector o)
  {
    inputSamples.addElement(i);
    outputSamples.addElement(o);
  }
  public void printSamples()
  {
    System.out.println(inputSamples+"->"+outputSamples);
  }
  public Vector recognize(Vector iS)
  {
    initInputs(iS);
    propagate();
    Vector oS = getOutput();
    return oS;
  }
  public void learn(int iterations,double globalError)
  {
    Enumeration iS;
    Enumeration oS;
    error = 0.0;
    int i=0;
    do
    {
      error = 0.0;
      iS = inputSamples.elements();
      oS = outputSamples.elements();
      while(iS.hasMoreElements()) {
       Vector temp = (Vector) oS.nextElement ();
       learnPattern((Vector)iS.nextElement(),temp);
       error += computeError (temp);
      }
      i++;

    }while(i<iterations&&globalError<error);
  }
  void learnPattern(Vector iS, Vector oS)
  {
    initInputs(iS);
    propagate();
    bpAdjustWeights(oS);
  }
  void initInputs(Vector iS)
  {
    Neuron neuron;
    Enumeration e = inputLayer.neurons.elements();
    Enumeration eS = iS.elements();
    while (eS.hasMoreElements())
    {
      neuron = (Neuron)e.nextElement();
      neuron.output = ((Double)eS.nextElement()).doubleValue();
    }
    neuron = (Neuron)e.nextElement(); // bias;
    neuron.output = 1.0;
  }
  void propagate()
  {
    Layer layer;
    Enumeration e = layers.elements();
    e.nextElement(); // skip the input layer
    while(e.hasMoreElements())
    {
      layer = (Layer)e.nextElement();
      layer.computeOutputs();
    }
  }
  public Vector getOutput()
  {
    Vector oS = new Vector();
    Neuron neuron;
    Enumeration e = outputLayer.neurons.elements();
    while(e.hasMoreElements())
    {
      neuron = (Neuron) e.nextElement();
      oS.addElement(new Double(neuron.getOutput()));
    }
    return oS;
  }
  double computeError(Vector oS)
  {
    Neuron neuron;
    double sum=0.0;
    double tmp;
    Enumeration e = outputLayer.neurons.elements();
    Enumeration eS = oS.elements();
    while (e.hasMoreElements())
    {
      neuron = (Neuron)e.nextElement();
      tmp = ((Double)eS.nextElement()).doubleValue() - neuron.getOutput();
      sum += tmp * tmp;
    }
    return sum/2.0;
  }
  double currentError() {
    return error;
  }
  void bpAdjustWeights(Vector oS)
  {
    outputLayer.computeBackpropDeltas(oS);
    for(int i=layers.size()-2; i>=1; i--)
     ((Layer)layers.elementAt(i)).computeBackpropDeltas();
    outputLayer.computeWeights();
    for(int i=layers.size()-2; i>=1; i--)
     ((Layer)layers.elementAt(i)).computeWeights();
  }
  void print()
  {
    Layer layer;
    Enumeration e = layers.elements();
    while(e.hasMoreElements())
    {
      layer = (Layer)e.nextElement();
      layer.print();
    }
  }
}
