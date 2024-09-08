import java.awt.*;

public class PictureTester
{
    public static void main(String[] args)
    {
        //construct a Picture object from a jpg image on disk
        Picture zeroBlue = new Picture ("beach.jpg");
        Picture keepOnlyBlue = new Picture("beach.jpg");
        Picture negate = new Picture("koala.jpg");
        Picture solarize = new Picture("waterlilies.jpg");
        Picture grayscale = new Picture("gorge.jpg");
        Picture tint = new Picture("beach.jpg");
        Picture posterize = new Picture("beach.jpg");
        Picture rightToLeft = new Picture("redMotorcycle.jpg");
        Picture horizontal = new Picture("redMotorcycle.jpg");
        Picture flip = new Picture("waterlilies.jpg");
        Picture fixRoof = new Picture("temple.jpg");
        Picture edge = new Picture("swan.jpg");
        Picture simpleBlur = new Picture("koala.jpg");
        Picture blur = new Picture("waterlilies.jpg");
        Picture glass = new Picture("waterlilies.jpg");

        zeroBlue.zeroBlue();
        keepOnlyBlue.keepOnlyBlue();
        negate.negate();
        solarize.solarize(127);
        grayscale.grayscale();
        tint.tint(1.25, 0.75, 1);
        posterize.posterize(63);
        rightToLeft.mirrorRightToLeft();
        horizontal.mirrorHorizontal();
        flip.verticalFlip();
        fixRoof.fixRoof();
        edge.edgeDetection(25);

        
        glass.glassFilter(5).view();
        blur.blur(5).view();
        simpleBlur.simpleBlur().view();
        testSteganography();
        testChromakey();
        edge.view();
        fixRoof.view();
        flip.view();
        horizontal.view();
        rightToLeft.view();
        posterize.view();
        tint.view();
        grayscale.view();
        solarize.view();
        negate.view();
        keepOnlyBlue.view();
        zeroBlue.view();   
    }   


    /** this method is static, you don't need to call it on an object (just "testChromekey()") */
    public static void testChromakey()
    {
        Picture one = new Picture("blue-mark.jpg");
        Picture two = new Picture("moon-surface.jpg");

        one.view(); //show original mustache guy picture
        two.view(); //show the untouched moon's surface pic

        one.chromakey(two, new Color(10, 40, 75), 60); //replace this color if within 60

        one.view();
    }

    /** this method is static, you don't need to call it on an object (just "testSteganography()") */
    public static void testSteganography()
    {
        Picture msg   = new Picture("msg.jpg");
        Picture beach = new Picture("beach.jpg");

        beach.encode(msg); //hide message in beach picture
        beach.view();      //beach w/ hidden message inside, shouldn't look different

        beach.decode().view(); //see the hidden message in the beach picture
    }
}