# Summary of 'Intelligence without representation', Rodney Brooks, 1991


    "Nobody talks about replicating the full gamut of human intelligence any
    more.  Instead we see a retreat into specialised subproblems, such as ways
    to represent knowledge, natural language understanding, vision, or even more
    specialized areas such as truth maintenance systems or plan verification."


* Why not biomimetics? Copy the way evolution slowly ramped up intelligence
  through simple reactive organisms interacting with the world, and see where
  that gets us?

    "I believe that mobility, acute vision, and the ability to carry out
    survival related tasks in a dynamic environment provide a necessary basis
    for the development of true intelligence"

* **NB:** Brooks *uses* the word intelligence, but he does not define
  intelligence.

* Contrived blocks world where researchers could represent the state of the
  world completely and explicitly

* Brooks drops certain 'tricks' throughout the paper. Slogans: "Good
  representation is the key to AI" -- in the case of this slogan, bandied around
  by Bobrow and Brown, Brooks posits that there "is certainly no AI vision
  program which can find arbitrary chairs in arbitrary images; they can at best
  find one particular type of chair in carefully selected images."

* Example of experimenter having to abstract away most of the details to form a
  simple description in terms of atomic concepts such as PERSON, CHAIR and
  BANANAS

    " But this abstraction is the essence of intelligence and the hard part of
    the problems being solved. [...] A truly intelligent program would study the
    photograph, perform the abstraction and solve the problem"


    "The problems of recognition, spatial understanding, dealing with sensor
    noise, partial models, etc. are all ignored."

* *Merkwelt*s. Brooks makes a nice argument about scientists proposing [We are
  merely doing good science] "by reducing the input data so that the program
  experiences the same perceptual world as humans".

    * Check out Uexküll -- "the *Merkwelt* we humans provide our programs is
      based on our own introspection. It is by no means clear that such a
      *Merkwelt* is anything like what we actually use internally -- it could
      just as easily be an output encoding for communication purposes (e.g.,
      most humans go through life never realizing, they have a large blind spot
      almost in the center of their visual fields)."

    * "The *Merkwelt* may not be anything like that used by humans. In fact, it
      may be the case that out introspective descriptions of our internal
      representations are completely misleading and quite different from what we
      really use"

Brooks proposes that the time-travelling Artificial Flight researchers are much
like humans looking at human intelligence and excitedly trying to implement it.
But, like the Flight researchers, we don't know what we've seen. Our Merkwelt
may be very much unlike what we actually experience, and so we are building on
poor foundations. The AF researchers forgot about aeronautics research and tried
to copy what they saw, but due to the incomplete picture they formed from their
visit, they inevitably missed crucial groundwork. Brooks proposes that through
biomimetics of evolution, we can perform said groundwork by learning from more
primitive forms of intelligence, and perhaps build on this. The AF researchers
are unaware of the real mechanisms at work in the airplane, much like humans are
unaware of their own blindspot in the center of their field of vision -- trying
to express what we observe, our Merkwelt, is necessarily constrained by our
limits of observation, and results in misinformation.

### IMPORTANT

    "A Creature should be **robust** with respect to it's environment; minor
    changes in the properties of the world should not lead to total collapse of
    the Creature's behaviour; rather one should expect only a gradual change in
    the capabilities of the Creature as the environment changes more and more"

#### Problem with naughty researchers

"When researchers working on a particular module get to choose both the inputs
and the outputs that specify the module requirements I believe there is little
chance the work they do will fit into a complete intelligent system"

### The Creature

#### Hierarchically layered architecture

1. AVOID: Senses objects in it's immediate vicinity and moves away from them,
   halting if it senses something in it's path. Connected to *feelforce* and
   *collide*

2. WANDER: The *wander* FSM generates a random heading every 10 seconds.
   Suppresses AVOID

3. EXPLORE: Look for distant visible places, and try to reach them. Suppresses
   WANDER

---

#### Advantages to the Subsumption architecture

* No single point of failure

* Once a layer is tested in the real world, adding a new layer just requires
  ensuring the interface is good. Any bugs only occur in the new layer.

See more in [@brooks86]

---

Brooks makes a weird point here:

* Can higher-level functions such as learning occur in these fixed topology
  networks of simple finite state machines?

No Rodney. No.

If he is saying what I think he is saying (that is: "Can learning occur without
representation?"), my answer is a big 'NO!'. The question he poses is phrased in
such a way that I can't be sure he means that. I can't be sure what he means, in
fact.
