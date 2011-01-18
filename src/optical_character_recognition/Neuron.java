package optical_character_recognition;

import java.util.*;

/**
 *
 * @author dombesz
 */
class Neuron
{
  public static double momentum = 0.9;
  public static double learningRate = 0.2;
  double output; // range from 0.0 to 1.0
  double sum;
  double delta;
  Vector inlinks;
  Vector outlinks;
  String label;

  public Neuron(String l)
  {
    output   = 0.0;
    delta    = 0.0;
    sum      = 0.0;
    inlinks  = new Vector();
    outlinks = new Vector();
    label    = new String(l);
  }
  public double getOutput()
  {
    return output;
  }
  public double getDelta()
  {
    return delta;
  }
  public void computeOutput()
  {
    Enumeration e = inlinks.elements();
    Synapse s;
    sum=0.0;
    while(e.hasMoreElements())
    {
      s = (Synapse)e.nextElement();
      sum += s.from.getOutput()*s.getWeight();
    }
    output = 1.0/(1.0 + Math.exp(-sum)); // sigmoid function
  }
  public void computeBackpropDelta(double d) // for an output neuron
  {
    delta = (d - output) * output * (1.0 - output);
  }
  public void computeBackpropDelta() // for a hidden neuron
  {
    double errorSum = 0.0;
    Synapse synapse;
    Enumeration e = outlinks.elements();
    while(e.hasMoreElements())
    {
      synapse = (Synapse)e.nextElement();
      errorSum += synapse.to.delta * synapse.getWeight();
    }
    delta = output * ( 1.0 - output) * errorSum;
  }
  public void computeWeight()
  {
    Synapse synapse;
    Enumeration e = inlinks.elements();
    while(e.hasMoreElements())
    {
      synapse = (Synapse)e.nextElement();
      synapse.data = learningRate*delta*synapse.from.getOutput()
                   + momentum*synapse.data;
      synapse.weight += synapse.data;
    }
  }
  public void print()
  {
    System.out.print(label+"="+output+": ");
    Synapse synapse;
    Enumeration e = outlinks.elements();
    while(e.hasMoreElements())
    {
      synapse = (Synapse)e.nextElement();
      System.out.print(synapse.to.label+"("+synapse.weight+") ");
    }
    System.out.println("");
  }
}
