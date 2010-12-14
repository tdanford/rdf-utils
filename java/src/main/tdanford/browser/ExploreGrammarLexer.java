// $ANTLR 3.3 Nov 30, 2010 12:50:56 ExploreGrammar.g 2010-12-13 16:28:41
 
package tdanford.browser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ExploreGrammarLexer extends Lexer {
    public static final int EOF=-1;
    public static final int PREFIX=4;
    public static final int FORWARDPROPS=5;
    public static final int REVERSEPROPS=6;
    public static final int FORWARDSTAR=7;
    public static final int REVERSESTAR=8;
    public static final int FORWARD=9;
    public static final int REVERSE=10;
    public static final int EVAL=11;
    public static final int LOOKUP=12;
    public static final int ALL=13;
    public static final int CHOOSE=14;
    public static final int PRETTYPRINT=15;
    public static final int LIST=16;
    public static final int DEFINE=17;
    public static final int DOLLAR=18;
    public static final int QUOTE=19;
    public static final int LANGLE=20;
    public static final int RANGLE=21;
    public static final int COLON=22;
    public static final int TYPED=23;
    public static final int SLASH=24;
    public static final int SIZE=25;
    public static final int BLANKTYPE=26;
    public static final int TYPEDLITERAL=27;
    public static final int WS=28;
    public static final int VARIABLE=29;
    public static final int KEYWORD=30;
    public static final int IRI=31;
    public static final int NEWLINE=32;
    public static final int PREFIXED=33;
    public static final int LITERAL=34;
    public static final int INTEGER=35;
    public static final int QUOTED=36;
    public static final int LETTER=37;
    public static final int DIGIT=38;

    // delegates
    // delegators

    public ExploreGrammarLexer() {;} 
    public ExploreGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ExploreGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "ExploreGrammar.g"; }

    // $ANTLR start "PREFIX"
    public final void mPREFIX() throws RecognitionException {
        try {
            int _type = PREFIX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:7:8: ( 'prefix' )
            // ExploreGrammar.g:7:10: 'prefix'
            {
            match("prefix"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PREFIX"

    // $ANTLR start "FORWARDPROPS"
    public final void mFORWARDPROPS() throws RecognitionException {
        try {
            int _type = FORWARDPROPS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:8:14: ( 'f?' )
            // ExploreGrammar.g:8:16: 'f?'
            {
            match("f?"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FORWARDPROPS"

    // $ANTLR start "REVERSEPROPS"
    public final void mREVERSEPROPS() throws RecognitionException {
        try {
            int _type = REVERSEPROPS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:9:14: ( 'r?' )
            // ExploreGrammar.g:9:16: 'r?'
            {
            match("r?"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REVERSEPROPS"

    // $ANTLR start "FORWARDSTAR"
    public final void mFORWARDSTAR() throws RecognitionException {
        try {
            int _type = FORWARDSTAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:10:13: ( 'f*' )
            // ExploreGrammar.g:10:15: 'f*'
            {
            match("f*"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FORWARDSTAR"

    // $ANTLR start "REVERSESTAR"
    public final void mREVERSESTAR() throws RecognitionException {
        try {
            int _type = REVERSESTAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:11:13: ( 'r*' )
            // ExploreGrammar.g:11:15: 'r*'
            {
            match("r*"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REVERSESTAR"

    // $ANTLR start "FORWARD"
    public final void mFORWARD() throws RecognitionException {
        try {
            int _type = FORWARD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:12:9: ( 'f' )
            // ExploreGrammar.g:12:11: 'f'
            {
            match('f'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FORWARD"

    // $ANTLR start "REVERSE"
    public final void mREVERSE() throws RecognitionException {
        try {
            int _type = REVERSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:13:9: ( 'r' )
            // ExploreGrammar.g:13:11: 'r'
            {
            match('r'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REVERSE"

    // $ANTLR start "EVAL"
    public final void mEVAL() throws RecognitionException {
        try {
            int _type = EVAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:14:6: ( 'g' )
            // ExploreGrammar.g:14:8: 'g'
            {
            match('g'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EVAL"

    // $ANTLR start "LOOKUP"
    public final void mLOOKUP() throws RecognitionException {
        try {
            int _type = LOOKUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:15:8: ( 'lbl' )
            // ExploreGrammar.g:15:10: 'lbl'
            {
            match("lbl"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOOKUP"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:16:5: ( 'all' )
            // ExploreGrammar.g:16:7: 'all'
            {
            match("all"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALL"

    // $ANTLR start "CHOOSE"
    public final void mCHOOSE() throws RecognitionException {
        try {
            int _type = CHOOSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:17:8: ( 'ch' )
            // ExploreGrammar.g:17:10: 'ch'
            {
            match("ch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHOOSE"

    // $ANTLR start "PRETTYPRINT"
    public final void mPRETTYPRINT() throws RecognitionException {
        try {
            int _type = PRETTYPRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:18:13: ( 'pp' )
            // ExploreGrammar.g:18:15: 'pp'
            {
            match("pp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRETTYPRINT"

    // $ANTLR start "LIST"
    public final void mLIST() throws RecognitionException {
        try {
            int _type = LIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:19:6: ( 'l' )
            // ExploreGrammar.g:19:8: 'l'
            {
            match('l'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LIST"

    // $ANTLR start "DEFINE"
    public final void mDEFINE() throws RecognitionException {
        try {
            int _type = DEFINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:20:8: ( 'def' )
            // ExploreGrammar.g:20:10: 'def'
            {
            match("def"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFINE"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:21:8: ( '$' )
            // ExploreGrammar.g:21:10: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:22:7: ( '\"' )
            // ExploreGrammar.g:22:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "LANGLE"
    public final void mLANGLE() throws RecognitionException {
        try {
            int _type = LANGLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:23:8: ( '<' )
            // ExploreGrammar.g:23:10: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LANGLE"

    // $ANTLR start "RANGLE"
    public final void mRANGLE() throws RecognitionException {
        try {
            int _type = RANGLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:24:8: ( '>' )
            // ExploreGrammar.g:24:10: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANGLE"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:25:7: ( ':' )
            // ExploreGrammar.g:25:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "TYPED"
    public final void mTYPED() throws RecognitionException {
        try {
            int _type = TYPED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:26:7: ( '^^' )
            // ExploreGrammar.g:26:9: '^^'
            {
            match("^^"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TYPED"

    // $ANTLR start "SLASH"
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:27:7: ( '/' )
            // ExploreGrammar.g:27:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "SIZE"
    public final void mSIZE() throws RecognitionException {
        try {
            int _type = SIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:28:6: ( '#' )
            // ExploreGrammar.g:28:8: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIZE"

    // $ANTLR start "PREFIXED"
    public final void mPREFIXED() throws RecognitionException {
        try {
            int _type = PREFIXED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:115:10: ( KEYWORD COLON KEYWORD )
            // ExploreGrammar.g:115:12: KEYWORD COLON KEYWORD
            {
            mKEYWORD(); 
            mCOLON(); 
            mKEYWORD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PREFIXED"

    // $ANTLR start "IRI"
    public final void mIRI() throws RecognitionException {
        try {
            int _type = IRI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:117:5: ( LANGLE (~ ( RANGLE | NEWLINE ) )+ RANGLE )
            // ExploreGrammar.g:117:7: LANGLE (~ ( RANGLE | NEWLINE ) )+ RANGLE
            {
            mLANGLE(); 
            // ExploreGrammar.g:117:14: (~ ( RANGLE | NEWLINE ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\t')||(LA1_0>='\u000B' && LA1_0<='=')||(LA1_0>='?' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ExploreGrammar.g:117:15: ~ ( RANGLE | NEWLINE )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='=')||(input.LA(1)>='?' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            mRANGLE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IRI"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:120:2: ( DOLLAR KEYWORD | DOLLAR INTEGER )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='$') ) {
                int LA2_1 = input.LA(2);

                if ( ((LA2_1>='A' && LA2_1<='Z')||(LA2_1>='a' && LA2_1<='z')) ) {
                    alt2=1;
                }
                else if ( ((LA2_1>='0' && LA2_1<='9')) ) {
                    alt2=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ExploreGrammar.g:120:4: DOLLAR KEYWORD
                    {
                    mDOLLAR(); 
                    mKEYWORD(); 

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:121:4: DOLLAR INTEGER
                    {
                    mDOLLAR(); 
                    mINTEGER(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "LITERAL"
    public final void mLITERAL() throws RecognitionException {
        try {
            int _type = LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:125:2: ( QUOTED )
            // ExploreGrammar.g:125:4: QUOTED
            {
            mQUOTED(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LITERAL"

    // $ANTLR start "KEYWORD"
    public final void mKEYWORD() throws RecognitionException {
        try {
            int _type = KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:128:9: ( LETTER ( LETTER | DIGIT | '_' | '-' | '.' )* )
            // ExploreGrammar.g:128:11: LETTER ( LETTER | DIGIT | '_' | '-' | '.' )*
            {
            mLETTER(); 
            // ExploreGrammar.g:128:18: ( LETTER | DIGIT | '_' | '-' | '.' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='-' && LA3_0<='.')||(LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ExploreGrammar.g:
            	    {
            	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:130:9: ( ( DIGIT )+ )
            // ExploreGrammar.g:130:11: ( DIGIT )+
            {
            // ExploreGrammar.g:130:11: ( DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ExploreGrammar.g:130:11: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "QUOTED"
    public final void mQUOTED() throws RecognitionException {
        try {
            // ExploreGrammar.g:132:17: ( QUOTE (~ QUOTE )+ QUOTE )
            // ExploreGrammar.g:132:19: QUOTE (~ QUOTE )+ QUOTE
            {
            mQUOTE(); 
            // ExploreGrammar.g:132:25: (~ QUOTE )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ExploreGrammar.g:132:26: ~ QUOTE
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\u0012')||(input.LA(1)>='\u0014' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            mQUOTE(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "QUOTED"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // ExploreGrammar.g:133:16: ( '0' .. '9' )
            // ExploreGrammar.g:133:18: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // ExploreGrammar.g:134:17: ( 'a' .. 'z' | 'A' .. 'Z' )
            // ExploreGrammar.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:136:9: ( '\\n' )
            // ExploreGrammar.g:136:11: '\\n'
            {
            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:138:4: ( ( ' ' | '\\u000C' )+ )
            // ExploreGrammar.g:138:6: ( ' ' | '\\u000C' )+
            {
            // ExploreGrammar.g:138:6: ( ' ' | '\\u000C' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\f'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ExploreGrammar.g:
            	    {
            	    if ( input.LA(1)=='\f'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

             

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // ExploreGrammar.g:1:8: ( PREFIX | FORWARDPROPS | REVERSEPROPS | FORWARDSTAR | REVERSESTAR | FORWARD | REVERSE | EVAL | LOOKUP | ALL | CHOOSE | PRETTYPRINT | LIST | DEFINE | DOLLAR | QUOTE | LANGLE | RANGLE | COLON | TYPED | SLASH | SIZE | PREFIXED | IRI | VARIABLE | LITERAL | KEYWORD | INTEGER | NEWLINE | WS )
        int alt7=30;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // ExploreGrammar.g:1:10: PREFIX
                {
                mPREFIX(); 

                }
                break;
            case 2 :
                // ExploreGrammar.g:1:17: FORWARDPROPS
                {
                mFORWARDPROPS(); 

                }
                break;
            case 3 :
                // ExploreGrammar.g:1:30: REVERSEPROPS
                {
                mREVERSEPROPS(); 

                }
                break;
            case 4 :
                // ExploreGrammar.g:1:43: FORWARDSTAR
                {
                mFORWARDSTAR(); 

                }
                break;
            case 5 :
                // ExploreGrammar.g:1:55: REVERSESTAR
                {
                mREVERSESTAR(); 

                }
                break;
            case 6 :
                // ExploreGrammar.g:1:67: FORWARD
                {
                mFORWARD(); 

                }
                break;
            case 7 :
                // ExploreGrammar.g:1:75: REVERSE
                {
                mREVERSE(); 

                }
                break;
            case 8 :
                // ExploreGrammar.g:1:83: EVAL
                {
                mEVAL(); 

                }
                break;
            case 9 :
                // ExploreGrammar.g:1:88: LOOKUP
                {
                mLOOKUP(); 

                }
                break;
            case 10 :
                // ExploreGrammar.g:1:95: ALL
                {
                mALL(); 

                }
                break;
            case 11 :
                // ExploreGrammar.g:1:99: CHOOSE
                {
                mCHOOSE(); 

                }
                break;
            case 12 :
                // ExploreGrammar.g:1:106: PRETTYPRINT
                {
                mPRETTYPRINT(); 

                }
                break;
            case 13 :
                // ExploreGrammar.g:1:118: LIST
                {
                mLIST(); 

                }
                break;
            case 14 :
                // ExploreGrammar.g:1:123: DEFINE
                {
                mDEFINE(); 

                }
                break;
            case 15 :
                // ExploreGrammar.g:1:130: DOLLAR
                {
                mDOLLAR(); 

                }
                break;
            case 16 :
                // ExploreGrammar.g:1:137: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 17 :
                // ExploreGrammar.g:1:143: LANGLE
                {
                mLANGLE(); 

                }
                break;
            case 18 :
                // ExploreGrammar.g:1:150: RANGLE
                {
                mRANGLE(); 

                }
                break;
            case 19 :
                // ExploreGrammar.g:1:157: COLON
                {
                mCOLON(); 

                }
                break;
            case 20 :
                // ExploreGrammar.g:1:163: TYPED
                {
                mTYPED(); 

                }
                break;
            case 21 :
                // ExploreGrammar.g:1:169: SLASH
                {
                mSLASH(); 

                }
                break;
            case 22 :
                // ExploreGrammar.g:1:175: SIZE
                {
                mSIZE(); 

                }
                break;
            case 23 :
                // ExploreGrammar.g:1:180: PREFIXED
                {
                mPREFIXED(); 

                }
                break;
            case 24 :
                // ExploreGrammar.g:1:189: IRI
                {
                mIRI(); 

                }
                break;
            case 25 :
                // ExploreGrammar.g:1:193: VARIABLE
                {
                mVARIABLE(); 

                }
                break;
            case 26 :
                // ExploreGrammar.g:1:202: LITERAL
                {
                mLITERAL(); 

                }
                break;
            case 27 :
                // ExploreGrammar.g:1:210: KEYWORD
                {
                mKEYWORD(); 

                }
                break;
            case 28 :
                // ExploreGrammar.g:1:218: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 29 :
                // ExploreGrammar.g:1:226: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 30 :
                // ExploreGrammar.g:1:234: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\1\27\1\34\1\37\1\40\1\42\3\27\1\46\1\50\1\52\5\uffff\1"+
        "\27\3\uffff\1\27\1\55\1\uffff\1\27\10\uffff\1\27\1\uffff\1\27\1"+
        "\60\1\27\6\uffff\1\27\1\uffff\1\63\1\64\1\uffff\1\65\1\27\3\uffff"+
        "\1\27\1\70\1\uffff";
    static final String DFA7_eofS =
        "\71\uffff";
    static final String DFA7_minS =
        "\1\12\1\55\2\52\5\55\1\60\2\0\5\uffff\1\55\3\uffff\2\55\1\uffff"+
        "\1\55\10\uffff\1\55\1\uffff\3\55\6\uffff\1\55\1\uffff\2\55\1\uffff"+
        "\2\55\3\uffff\2\55\1\uffff";
    static final String DFA7_maxS =
        "\12\172\2\uffff\5\uffff\1\172\3\uffff\2\172\1\uffff\1\172\10\uffff"+
        "\1\172\1\uffff\3\172\6\uffff\1\172\1\uffff\2\172\1\uffff\2\172\3"+
        "\uffff\2\172\1\uffff";
    static final String DFA7_acceptS =
        "\14\uffff\1\22\1\23\1\24\1\25\1\26\1\uffff\1\34\1\35\1\36\2\uffff"+
        "\1\33\1\uffff\1\27\1\2\1\4\1\6\1\3\1\5\1\7\1\10\1\uffff\1\15\3\uffff"+
        "\1\17\1\31\1\20\1\32\1\21\1\30\1\uffff\1\14\2\uffff\1\13\2\uffff"+
        "\1\11\1\12\1\16\2\uffff\1\1";
    static final String DFA7_specialS =
        "\12\uffff\1\1\1\0\55\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\23\1\uffff\1\24\23\uffff\1\24\1\uffff\1\12\1\20\1\11\12\uffff"+
            "\1\17\12\22\1\15\1\uffff\1\13\1\uffff\1\14\2\uffff\32\21\3\uffff"+
            "\1\16\2\uffff\1\6\1\21\1\7\1\10\1\21\1\2\1\4\4\21\1\5\3\21\1"+
            "\1\1\21\1\3\10\21",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\17\30\1\26\1\30\1\25\10\30",
            "\1\33\2\uffff\2\30\1\uffff\12\30\1\31\4\uffff\1\32\1\uffff"+
            "\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\36\2\uffff\2\30\1\uffff\12\30\1\31\4\uffff\1\35\1\uffff"+
            "\32\30\4\uffff\1\30\1\uffff\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\1\30\1\41\30\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\13\30\1\43\16\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\7\30\1\44\22\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\4\30\1\45\25\30",
            "\12\47\7\uffff\32\47\6\uffff\32\47",
            "\42\51\1\uffff\uffdd\51",
            "\12\53\1\uffff\63\53\1\uffff\uffc1\53",
            "",
            "",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\4\30\1\54\25\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\13\30\1\56\16\30",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\13\30\1\57\16\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\5\30\1\61\24\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\5\30\1\62\24\30",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\10\30\1\66\21\30",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\27\30\1\67\2\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( PREFIX | FORWARDPROPS | REVERSEPROPS | FORWARDSTAR | REVERSESTAR | FORWARD | REVERSE | EVAL | LOOKUP | ALL | CHOOSE | PRETTYPRINT | LIST | DEFINE | DOLLAR | QUOTE | LANGLE | RANGLE | COLON | TYPED | SLASH | SIZE | PREFIXED | IRI | VARIABLE | LITERAL | KEYWORD | INTEGER | NEWLINE | WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_11 = input.LA(1);

                        s = -1;
                        if ( ((LA7_11>='\u0000' && LA7_11<='\t')||(LA7_11>='\u000B' && LA7_11<='=')||(LA7_11>='?' && LA7_11<='\uFFFF')) ) {s = 43;}

                        else s = 42;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA7_10 = input.LA(1);

                        s = -1;
                        if ( ((LA7_10>='\u0000' && LA7_10<='!')||(LA7_10>='#' && LA7_10<='\uFFFF')) ) {s = 41;}

                        else s = 40;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}