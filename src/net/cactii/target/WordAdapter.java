package net.cactii.target;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WordAdapter extends BaseAdapter {

  private LayoutInflater inflater;
  private ArrayList<PlayerWord> playerWords = new ArrayList<PlayerWord>();
  private Typeface face;
  
  public WordAdapter(Activity context, ArrayList<PlayerWord>wordList) {
    super();
    this.inflater = LayoutInflater.from(context);
    this.playerWords = wordList;
    this.face=Typeface.createFromAsset(MainActivity.currentInstance.getAssets(), "fonts/font.ttf");
  }

  @Override
  public int getCount() {
    return playerWords.size();
  }

  @Override
  public Object getItem(int item) {
    return playerWords.get(item);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View returnView, ViewGroup parent) {
    returnView = inflater.inflate(R.layout.playerlistitem, null);
    PlayerWord playerWord = this.playerWords.get(position);
    TextView playerWordItem = (TextView)returnView.findViewById(R.id.playerListItem);
    TextView playerWordItemResult = (TextView)returnView.findViewById(R.id.playerListItemResult);
    LinearLayout wordRow = (LinearLayout)returnView.findViewById(R.id.wordRow);
    playerWordItem.setTypeface(this.face);
    playerWordItem.setText(playerWord.word);
    switch(playerWord.result) {
      case PlayerWord.RESULT_INVALID :
        playerWordItemResult.setText("INVALID");
        wordRow.setBackgroundColor(0x90FFB0B0);
        break;
      case PlayerWord.RESULT_MISSED :
        wordRow.setBackgroundColor(0x8FFFFFA0);
        break;
      case PlayerWord.RESULT_OK :
        playerWordItemResult.setText("OK");
        break;
      case PlayerWord.RESULT_HEADER :
        wordRow.setBackgroundColor(0x8FFFFFFF);
        playerWordItem.setText("");
        playerWordItemResult.setText(playerWord.word);
        break;
      default :
        playerWordItemResult.setText("");
      break;
    }
    return returnView;
  }
}
