// $ANTLR 3.3 Nov 30, 2010 12:50:56 ExploreGrammar.g 2010-12-13 14:16:09
 
package tdanford.browser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ExploreGrammarLexer extends Lexer {
    public static final int EOF=-1;
    public static final int FORWARDPROPS=4;
    public static final int REVERSEPROPS=5;
    public static final int FORWARDSTAR=6;
    public static final int REVERSESTAR=7;
    public static final int FORWARD=8;
    public static final int REVERSE=9;
    public static final int EVAL=10;
    public static final int LOOKUP=11;
    public static final int ALL=12;
    public static final int CHOOSE=13;
    public static final int PRETTYPRINT=14;
    public static final int LIST=15;
    public static final int DEFINE=16;
    public static final int DOLLAR=17;
    public static final int QUOTE=18;
    public static final int LANGLE=19;
    public static final int RANGLE=20;
    public static final int COLON=21;
    public static final int TYPED=22;
    public static final int SLASH=23;
    public static final int SIZE=24;
    public static final int BLANKTYPE=25;
    public static final int TYPEDLITERAL=26;
    public static final int WS=27;
    public static final int VARIABLE=28;
    public static final int KEYWORD=29;
    public static final int NEWLINE=30;
    public static final int PREFIXED=31;
    public static final int IRI=32;
    public static final int LITERAL=33;
    public static final int QUOTED=34;
    public static final int LETTER=35;
    public static final int DIGIT=36;
    public static final int INTEGER=37;

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

    // $ANTLR start "FORWARDPROPS"
    public final void mFORWARDPROPS() throws RecognitionException {
        try {
            int _type = FORWARDPROPS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ExploreGrammar.g:7:14: ( 'f?' )
            // ExploreGrammar.g:7:16: 'f?'
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
            // ExploreGrammar.g:8:14: ( 'r?' )
            // ExploreGrammar.g:8:16: 'r?'
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
            // ExploreGrammar.g:9:13: ( 'f*' )
            // ExploreGrammar.g:9:15: 'f*'
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
            // ExploreGrammar.g:10:13: ( 'r*' )
            // ExploreGrammar.g:10:15: 'r*'
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
            // ExploreGrammar.g:11:9: ( 'f' )
            // ExploreGrammar.g:11:11: 'f'
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
            // ExploreGrammar.g:12:9: ( 'r' )
            // ExploreGrammar.g:12:11: 'r'
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
            // ExploreGrammar.g:13:6: ( 'g' )
            // ExploreGrammar.g:13:8: 'g'
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
            // ExploreGrammar.g:14:8: ( 'lbl' )
            // ExploreGrammar.g:14:10: 'lbl'
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
            // ExploreGrammar.g:15:5: ( 'all' )
            // ExploreGrammar.g:15:7: 'all'
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
            // ExploreGrammar.g:16:8: ( 'ch' )
            // ExploreGrammar.g:16:10: 'ch'
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
            // ExploreGrammar.g:17:13: ( 'pp' )
            // ExploreGrammar.g:17:15: 'pp'
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
            // ExploreGrammar.g:18:6: ( 'l' )
            // ExploreGrammar.g:18:8: 'l'
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
            // ExploreGrammar.g:19:8: ( 'def' )
            // ExploreGrammar.g:19:10: 'def'
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
            // ExploreGrammar.g:20:8: ( '$' )
            // ExploreGrammar.g:20:10: '$'
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
            // ExploreGrammar.g:21:7: ( '\"' )
            // ExploreGrammar.g:21:9: '\"'
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
            // ExploreGrammar.g:22:8: ( '<' )
            // ExploreGrammar.g:22:10: '<'
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
            // ExploreGrammar.g:23:8: ( '>' )
            // ExploreGrammar.g:23:10: '>'
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
            // ExploreGrammar.g:24:7: ( ':' )
            // ExploreGrammar.g:24:9: ':'
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
            // ExploreGrammar.g:25:7: ( '^^' )
            // ExploreGrammar.g:25:9: '^^'
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
            // ExploreGrammar.g:26:7: ( '/' )
            // ExploreGrammar.g:26:9: '/'
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
            // ExploreGrammar.g:27:6: ( '#' )
            // ExploreGrammar.g:27:8: '#'
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
            // ExploreGrammar.g:112:10: ( KEYWORD COLON KEYWORD )
            // ExploreGrammar.g:112:12: KEYWORD COLON KEYWORD
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
            // ExploreGrammar.g:114:5: ( LANGLE (~ ( RANGLE | NEWLINE ) )+ RANGLE )
            // ExploreGrammar.g:114:7: LANGLE (~ ( RANGLE | NEWLINE ) )+ RANGLE
            {
            mLANGLE(); 
            // ExploreGrammar.g:114:14: (~ ( RANGLE | NEWLINE ) )+
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
            	    // ExploreGrammar.g:114:15: ~ ( RANGLE | NEWLINE )
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
            // ExploreGrammar.g:116:10: ( DOLLAR KEYWORD )
            // ExploreGrammar.g:116:12: DOLLAR KEYWORD
            {
            mDOLLAR(); 
            mKEYWORD(); 

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
            // ExploreGrammar.g:119:2: ( QUOTED )
            // ExploreGrammar.g:119:4: QUOTED
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
            // ExploreGrammar.g:122:9: ( LETTER ( LETTER | DIGIT | '_' | '-' | '.' )* )
            // ExploreGrammar.g:122:11: LETTER ( LETTER | DIGIT | '_' | '-' | '.' )*
            {
            mLETTER(); 
            // ExploreGrammar.g:122:18: ( LETTER | DIGIT | '_' | '-' | '.' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='-' && LA2_0<='.')||(LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
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
            	    break loop2;
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
            // ExploreGrammar.g:124:9: ( ( DIGIT )+ )
            // ExploreGrammar.g:124:11: ( DIGIT )+
            {
            // ExploreGrammar.g:124:11: ( DIGIT )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ExploreGrammar.g:124:11: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
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
            // ExploreGrammar.g:126:17: ( QUOTE (~ QUOTE )+ QUOTE )
            // ExploreGrammar.g:126:19: QUOTE (~ QUOTE )+ QUOTE
            {
            mQUOTE(); 
            // ExploreGrammar.g:126:25: (~ QUOTE )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ExploreGrammar.g:126:26: ~ QUOTE
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\u0011')||(input.LA(1)>='\u0013' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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
            // ExploreGrammar.g:127:16: ( '0' .. '9' )
            // ExploreGrammar.g:127:18: '0' .. '9'
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
            // ExploreGrammar.g:128:17: ( 'a' .. 'z' | 'A' .. 'Z' )
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
            // ExploreGrammar.g:130:9: ( '\\n' )
            // ExploreGrammar.g:130:11: '\\n'
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
            // ExploreGrammar.g:132:4: ( ( ' ' | '\\u000C' )+ )
            // ExploreGrammar.g:132:6: ( ' ' | '\\u000C' )+
            {
            // ExploreGrammar.g:132:6: ( ' ' | '\\u000C' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\f'||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
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
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
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
        // ExploreGrammar.g:1:8: ( FORWARDPROPS | REVERSEPROPS | FORWARDSTAR | REVERSESTAR | FORWARD | REVERSE | EVAL | LOOKUP | ALL | CHOOSE | PRETTYPRINT | LIST | DEFINE | DOLLAR | QUOTE | LANGLE | RANGLE | COLON | TYPED | SLASH | SIZE | PREFIXED | IRI | VARIABLE | LITERAL | KEYWORD | INTEGER | NEWLINE | WS )
        int alt6=29;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // ExploreGrammar.g:1:10: FORWARDPROPS
                {
                mFORWARDPROPS(); 

                }
                break;
            case 2 :
                // ExploreGrammar.g:1:23: REVERSEPROPS
                {
                mREVERSEPROPS(); 

                }
                break;
            case 3 :
                // ExploreGrammar.g:1:36: FORWARDSTAR
                {
                mFORWARDSTAR(); 

                }
                break;
            case 4 :
                // ExploreGrammar.g:1:48: REVERSESTAR
                {
                mREVERSESTAR(); 

                }
                break;
            case 5 :
                // ExploreGrammar.g:1:60: FORWARD
                {
                mFORWARD(); 

                }
                break;
            case 6 :
                // ExploreGrammar.g:1:68: REVERSE
                {
                mREVERSE(); 

                }
                break;
            case 7 :
                // ExploreGrammar.g:1:76: EVAL
                {
                mEVAL(); 

                }
                break;
            case 8 :
                // ExploreGrammar.g:1:81: LOOKUP
                {
                mLOOKUP(); 

                }
                break;
            case 9 :
                // ExploreGrammar.g:1:88: ALL
                {
                mALL(); 

                }
                break;
            case 10 :
                // ExploreGrammar.g:1:92: CHOOSE
                {
                mCHOOSE(); 

                }
                break;
            case 11 :
                // ExploreGrammar.g:1:99: PRETTYPRINT
                {
                mPRETTYPRINT(); 

                }
                break;
            case 12 :
                // ExploreGrammar.g:1:111: LIST
                {
                mLIST(); 

                }
                break;
            case 13 :
                // ExploreGrammar.g:1:116: DEFINE
                {
                mDEFINE(); 

                }
                break;
            case 14 :
                // ExploreGrammar.g:1:123: DOLLAR
                {
                mDOLLAR(); 

                }
                break;
            case 15 :
                // ExploreGrammar.g:1:130: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 16 :
                // ExploreGrammar.g:1:136: LANGLE
                {
                mLANGLE(); 

                }
                break;
            case 17 :
                // ExploreGrammar.g:1:143: RANGLE
                {
                mRANGLE(); 

                }
                break;
            case 18 :
                // ExploreGrammar.g:1:150: COLON
                {
                mCOLON(); 

                }
                break;
            case 19 :
                // ExploreGrammar.g:1:156: TYPED
                {
                mTYPED(); 

                }
                break;
            case 20 :
                // ExploreGrammar.g:1:162: SLASH
                {
                mSLASH(); 

                }
                break;
            case 21 :
                // ExploreGrammar.g:1:168: SIZE
                {
                mSIZE(); 

                }
                break;
            case 22 :
                // ExploreGrammar.g:1:173: PREFIXED
                {
                mPREFIXED(); 

                }
                break;
            case 23 :
                // ExploreGrammar.g:1:182: IRI
                {
                mIRI(); 

                }
                break;
            case 24 :
                // ExploreGrammar.g:1:186: VARIABLE
                {
                mVARIABLE(); 

                }
                break;
            case 25 :
                // ExploreGrammar.g:1:195: LITERAL
                {
                mLITERAL(); 

                }
                break;
            case 26 :
                // ExploreGrammar.g:1:203: KEYWORD
                {
                mKEYWORD(); 

                }
                break;
            case 27 :
                // ExploreGrammar.g:1:211: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 28 :
                // ExploreGrammar.g:1:219: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 29 :
                // ExploreGrammar.g:1:227: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\1\27\1\34\1\35\1\37\4\41\1\45\1\47\1\51\5\uffff\1\41\6"+
        "\uffff\1\41\5\uffff\1\41\1\uffff\1\41\1\uffff\1\55\1\56\1\41\6\uffff"+
        "\1\60\1\61\2\uffff\1\62\3\uffff";
    static final String DFA6_eofS =
        "\63\uffff";
    static final String DFA6_minS =
        "\1\12\2\52\6\55\1\101\2\0\5\uffff\1\55\6\uffff\1\55\5\uffff\1\55"+
        "\1\uffff\1\55\1\uffff\3\55\6\uffff\2\55\2\uffff\1\55\3\uffff";
    static final String DFA6_maxS =
        "\12\172\2\uffff\5\uffff\1\172\6\uffff\1\172\5\uffff\1\172\1\uffff"+
        "\1\172\1\uffff\3\172\6\uffff\2\172\2\uffff\1\172\3\uffff";
    static final String DFA6_acceptS =
        "\14\uffff\1\21\1\22\1\23\1\24\1\25\1\uffff\1\33\1\34\1\35\1\1\1"+
        "\3\1\5\1\uffff\1\26\1\2\1\4\1\6\1\7\1\uffff\1\14\1\uffff\1\32\3"+
        "\uffff\1\16\1\30\1\17\1\31\1\20\1\27\2\uffff\1\12\1\13\1\uffff\1"+
        "\10\1\11\1\15";
    static final String DFA6_specialS =
        "\12\uffff\1\0\1\1\47\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\23\1\uffff\1\24\23\uffff\1\24\1\uffff\1\12\1\20\1\11\12\uffff"+
            "\1\17\12\22\1\15\1\uffff\1\13\1\uffff\1\14\2\uffff\32\21\3\uffff"+
            "\1\16\2\uffff\1\5\1\21\1\6\1\10\1\21\1\1\1\3\4\21\1\4\3\21\1"+
            "\7\1\21\1\2\10\21",
            "\1\26\2\uffff\2\30\1\uffff\12\30\1\31\4\uffff\1\25\1\uffff"+
            "\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\33\2\uffff\2\30\1\uffff\12\30\1\31\4\uffff\1\32\1\uffff"+
            "\32\30\4\uffff\1\30\1\uffff\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\1\30\1\36\30\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\13\30\1\40\16\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\7\30\1\42\22\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\17\30\1\43\12\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\4\30\1\44\25\30",
            "\32\46\6\uffff\32\46",
            "\42\50\1\uffff\uffdd\50",
            "\12\52\1\uffff\63\52\1\uffff\uffc1\52",
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
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\13\30\1\53\16\30",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\13\30\1\54\16\30",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\5\30\1\57\24\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "",
            "\2\30\1\uffff\12\30\1\31\6\uffff\32\30\4\uffff\1\30\1\uffff"+
            "\32\30",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( FORWARDPROPS | REVERSEPROPS | FORWARDSTAR | REVERSESTAR | FORWARD | REVERSE | EVAL | LOOKUP | ALL | CHOOSE | PRETTYPRINT | LIST | DEFINE | DOLLAR | QUOTE | LANGLE | RANGLE | COLON | TYPED | SLASH | SIZE | PREFIXED | IRI | VARIABLE | LITERAL | KEYWORD | INTEGER | NEWLINE | WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA6_10 = input.LA(1);

                        s = -1;
                        if ( ((LA6_10>='\u0000' && LA6_10<='!')||(LA6_10>='#' && LA6_10<='\uFFFF')) ) {s = 40;}

                        else s = 39;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA6_11 = input.LA(1);

                        s = -1;
                        if ( ((LA6_11>='\u0000' && LA6_11<='\t')||(LA6_11>='\u000B' && LA6_11<='=')||(LA6_11>='?' && LA6_11<='\uFFFF')) ) {s = 42;}

                        else s = 41;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 6, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}