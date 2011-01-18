package optical_character_recognition;

import java.util.*;

/**
 *
 * @author dombesz
 */
class Synapse
{
  double weight;
  double data;
  Neuron from;
  Neuron to;
  static Random random = new Random();

  Synapse(Neuron f,Neuron t)
  {
    from   = f;
    to     = t;
    weight = random.nextDouble() / 5.0;
    data   = 0.0;
    f.outlinks.addElement(this);
    t.inlinks.addElement(this);
  }
  public double getWeight()
  {
    return weight;
  }
}
