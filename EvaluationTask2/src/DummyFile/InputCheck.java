package DummyFile;

class InputCheck {				
	
	private String str;			
				
	public InputCheck(String str) {			
		if(str == null) {		
			str = "";	
		}		
		//isNumericやcheckSizeメソッドではInputCheckクラスのstrフィールドを参照していて、元のコードではnullになってしまい、24行目のthis.str.lengthがnullの長さを取得しようとしてエラーとなっていた。
		//引数のstrの値を再度引数のstrに代入していたため、this.をつけてInputCheckクラスのstrフィールドに代入されるように変更
		//全角数字の場合は半角数字に変換して代入（個人練習）
		this.str = toHalfWidth(str);		
	}			
				
protected boolean isNumeric()				
{				
	//空文字をコンソールから入力時に処理が終了しない不具合
	//元々 ==　で空文字比較をしていたため、オブジェクトとしての参照先の比較結果がfalseとなり戻り値のfalseが返されていたなかった
	//isEmptyを使った空文字チェックに切り替えて、空文字が入力された場合はfalseを返し「エラー：数値のみ入力してください。」が出力されるようにした
	if (this.str.isEmpty()) {			
		return false;		
	}
				
	for (int i = 0; i < this.str.length(); i++)			
	{
		char c = this.str.charAt(i);		
		if (c < '0' || c > '9') {		
			return false;	
		}
	}			
	return true;			
}				
				
protected boolean checkSize() {	
	if(this.str.length() <= 10) {			
		if(Math.abs(Integer.parseInt(this.str)) <= 1024*1024*100) {		
			return true;	
		}		
		return false;		
	}			
	else {			
		return false;		
	}			
}				

//全角数字を半角に変換（個人練習）
private String toHalfWidth(String str) {
	  StringBuilder sb = new StringBuilder(str);
	  for (int i = 0; i < str.length(); i++) {
	    char c = str.charAt(i);
	    if (0xFF10 <= c && c <= 0xFF19) {
	      sb.setCharAt(i, (char) (c - 0xFEE0));
	    }
	  }
	  return this.str = sb.toString();
	}
}				
