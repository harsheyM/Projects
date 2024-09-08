public class GuitarHero
{
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static GuitarString[] strings = new GuitarString[37];
    public static void main(String[] args) {
        for (int i = 0; i < 37; i++)
        {
            strings[i] = new GuitarString(440 * Math.pow(1.05956, i -24));
        }
        
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                 char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) >= 0) { strings[keyboard.indexOf(key2)].pluck(); }
                
            }
            double sample = 0;
            for (int i = 0; i < strings.length; i++)
            {
                sample += strings[i].sample();
                strings[i].tic();
            }
            StdAudio.play(sample);
        }
}
}