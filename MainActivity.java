//K.A.J.
//Please see RGBretriever for the color
public class MainActivity extends AppCompatActivity {
    private String s = "                ";
    private String backgroundColor = "#AFEEEE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is the Layout
        Log.w("On Create", "Creating RelativeLayout--------------------");
        final RelativeLayout rLayout = new RelativeLayout(this);
        rLayout.setBackgroundColor(Color.parseColor(backgroundColor));

        //This is the button
        Log.w("On Create", "Creating button--------------------");
        final Button button = getButton("Traffic Light", 20, rLayout);

        //These are the three labels
        final TextView l1 = getLabel(100, 40, rLayout, s);
        final TextView l2 = getLabel(350, 40, rLayout, s);
        final TextView l3 = getLabel(600, 40, rLayout, s);
        
        //This is where the color of the label is changed
        Log.w("On Create", "changing color onClick--------------------");
        button.setOnClickListener(new View.OnClickListener() {
            int[] colors = {Color.GREEN, Color.YELLOW, Color.RED};
            int colorCount = 0;
            int n = 0;


            @Override
            public void onClick(View view) {
                l1.setBackgroundColor(Color.parseColor(backgroundColor));
                l2.setBackgroundColor(Color.parseColor(backgroundColor));
                l3.setBackgroundColor(Color.parseColor(backgroundColor));

                if (n > 5 && n < 10) {
                    RGBretriever rgbRetriever = new RGBretriever(0, 0, 0);
                    final Random rand = new Random();

                    button.setText("Why are you still clicking?");
                    button.setBackgroundColor(Color.parseColor(rgbRetriever.getRgbCode(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));

                    colorCount = changeLabelColors(colorCount, colors, l1, l2, l3);
                } else if (n > 10) {
                    button.setBackgroundColor(Color.WHITE);
                    colorCount = changeLabelColors(colorCount, colors, l1, l2, l3);
                } else {
                    colorCount = changeLabelColors(colorCount, colors, l1, l2, l3);
                }

                colorCount++;
                n++;
            }
        }
        );

        setContentView(rLayout);
    }

    public int changeLabelColors(int count, int[] colorArray, TextView t1, TextView t2, TextView t3) {
        if (count >= colorArray.length) {
            count = 0;
            t1.setBackgroundColor(colorArray[count]);
        } else {
            if (count == 0) {
                t1.setBackgroundColor(colorArray[count]);
            } else if (count == 1) {
                t2.setBackgroundColor(colorArray[count]);
            } else {
                t3.setBackgroundColor(colorArray[count]);
            }
        }
        return count;
    }

    //Button Methods
    public RelativeLayout.LayoutParams getButtonLayout() {
        Log.w("On Create", "Creating button parameters--------------------");
        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        Log.w("On Create", "Adding rules--------------------");
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

        return buttonParams;
    }

    public Button getButton(String s1, int textSize, RelativeLayout r) {
        RelativeLayout.LayoutParams buttonParams = getButtonLayout();

        Button b = new Button(this);
        b.setText(s1);
        b.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        b.setBackgroundColor(Color.GRAY);

        r.addView(b, buttonParams);

        return b;
    }

    //Label Methods
    public RelativeLayout.LayoutParams getLabelLayout(int m) {
        RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        labelParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        labelParams.setMargins(0, m, 0, 0);

        return labelParams;
    }

    public TextView getLabel(int m, int textSize, RelativeLayout r, String s) {
        RelativeLayout.LayoutParams labelParams = getLabelLayout(m);

        final TextView l = new TextView(this);

        l.setText(s);
        l.setBackgroundColor(Color.parseColor(backgroundColor));
        l.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        r.addView(l, labelParams);

        return l;
    }
    }
