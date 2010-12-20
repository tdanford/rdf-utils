// $ANTLR 3.3 Nov 30, 2010 12:50:56 ExploreGrammar.g 2010-12-14 13:46:25
 
package tdanford.browser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class ExploreGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PREFIX", "CLEAR", "FORWARDPROPS", "REVERSEPROPS", "FORWARDSTAR", "REVERSESTAR", "FORWARD", "REVERSE", "EVAL", "LOOKUP", "ALL", "CHOOSE", "PRETTYPRINT", "LIST", "DEFINE", "DOLLAR", "QUOTE", "LANGLE", "RANGLE", "COLON", "TYPED", "SLASH", "SIZE", "BLANKTYPE", "TYPEDLITERAL", "WS", "VARIABLE", "KEYWORD", "IRI", "NEWLINE", "PREFIXED", "LITERAL", "INTEGER", "QUOTED", "LETTER", "DIGIT"
    };
    public static final int EOF=-1;
    public static final int PREFIX=4;
    public static final int CLEAR=5;
    public static final int FORWARDPROPS=6;
    public static final int REVERSEPROPS=7;
    public static final int FORWARDSTAR=8;
    public static final int REVERSESTAR=9;
    public static final int FORWARD=10;
    public static final int REVERSE=11;
    public static final int EVAL=12;
    public static final int LOOKUP=13;
    public static final int ALL=14;
    public static final int CHOOSE=15;
    public static final int PRETTYPRINT=16;
    public static final int LIST=17;
    public static final int DEFINE=18;
    public static final int DOLLAR=19;
    public static final int QUOTE=20;
    public static final int LANGLE=21;
    public static final int RANGLE=22;
    public static final int COLON=23;
    public static final int TYPED=24;
    public static final int SLASH=25;
    public static final int SIZE=26;
    public static final int BLANKTYPE=27;
    public static final int TYPEDLITERAL=28;
    public static final int WS=29;
    public static final int VARIABLE=30;
    public static final int KEYWORD=31;
    public static final int IRI=32;
    public static final int NEWLINE=33;
    public static final int PREFIXED=34;
    public static final int LITERAL=35;
    public static final int INTEGER=36;
    public static final int QUOTED=37;
    public static final int LETTER=38;
    public static final int DIGIT=39;

    // delegates
    // delegators


        public ExploreGrammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ExploreGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return ExploreGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "ExploreGrammar.g"; }


    public static class program_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // ExploreGrammar.g:51:1: program : ( ( WS )? command ( WS command )* ( WS )? EOF -> ( command )+ | ( WS )? interpreter_command ( WS )? EOF -> interpreter_command );
    public final ExploreGrammarParser.program_return program() throws RecognitionException {
        ExploreGrammarParser.program_return retval = new ExploreGrammarParser.program_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS1=null;
        Token WS3=null;
        Token WS5=null;
        Token EOF6=null;
        Token WS7=null;
        Token WS9=null;
        Token EOF10=null;
        ExploreGrammarParser.command_return command2 = null;

        ExploreGrammarParser.command_return command4 = null;

        ExploreGrammarParser.interpreter_command_return interpreter_command8 = null;


        Object WS1_tree=null;
        Object WS3_tree=null;
        Object WS5_tree=null;
        Object EOF6_tree=null;
        Object WS7_tree=null;
        Object WS9_tree=null;
        Object EOF10_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_interpreter_command=new RewriteRuleSubtreeStream(adaptor,"rule interpreter_command");
        RewriteRuleSubtreeStream stream_command=new RewriteRuleSubtreeStream(adaptor,"rule command");
        try {
            // ExploreGrammar.g:52:2: ( ( WS )? command ( WS command )* ( WS )? EOF -> ( command )+ | ( WS )? interpreter_command ( WS )? EOF -> interpreter_command )
            int alt6=2;
            switch ( input.LA(1) ) {
            case WS:
                {
                int LA6_1 = input.LA(2);

                if ( ((LA6_1>=FORWARDPROPS && LA6_1<=CHOOSE)) ) {
                    alt6=1;
                }
                else if ( (LA6_1==SLASH) ) {
                    alt6=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
                }
                break;
            case FORWARDPROPS:
            case REVERSEPROPS:
            case FORWARDSTAR:
            case REVERSESTAR:
            case FORWARD:
            case REVERSE:
            case EVAL:
            case LOOKUP:
            case ALL:
            case CHOOSE:
                {
                alt6=1;
                }
                break;
            case SLASH:
                {
                alt6=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ExploreGrammar.g:52:4: ( WS )? command ( WS command )* ( WS )? EOF
                    {
                    // ExploreGrammar.g:52:4: ( WS )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==WS) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // ExploreGrammar.g:52:4: WS
                            {
                            WS1=(Token)match(input,WS,FOLLOW_WS_in_program311);  
                            stream_WS.add(WS1);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_command_in_program314);
                    command2=command();

                    state._fsp--;

                    stream_command.add(command2.getTree());
                    // ExploreGrammar.g:52:16: ( WS command )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==WS) ) {
                            int LA2_1 = input.LA(2);

                            if ( ((LA2_1>=FORWARDPROPS && LA2_1<=CHOOSE)) ) {
                                alt2=1;
                            }


                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ExploreGrammar.g:52:17: WS command
                    	    {
                    	    WS3=(Token)match(input,WS,FOLLOW_WS_in_program317);  
                    	    stream_WS.add(WS3);

                    	    pushFollow(FOLLOW_command_in_program319);
                    	    command4=command();

                    	    state._fsp--;

                    	    stream_command.add(command4.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    // ExploreGrammar.g:52:30: ( WS )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==WS) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ExploreGrammar.g:52:30: WS
                            {
                            WS5=(Token)match(input,WS,FOLLOW_WS_in_program323);  
                            stream_WS.add(WS5);


                            }
                            break;

                    }

                    EOF6=(Token)match(input,EOF,FOLLOW_EOF_in_program326);  
                    stream_EOF.add(EOF6);



                    // AST REWRITE
                    // elements: command
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 52:38: -> ( command )+
                    {
                        if ( !(stream_command.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_command.hasNext() ) {
                            adaptor.addChild(root_0, stream_command.nextTree());

                        }
                        stream_command.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:53:4: ( WS )? interpreter_command ( WS )? EOF
                    {
                    // ExploreGrammar.g:53:4: ( WS )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==WS) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // ExploreGrammar.g:53:4: WS
                            {
                            WS7=(Token)match(input,WS,FOLLOW_WS_in_program336);  
                            stream_WS.add(WS7);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_interpreter_command_in_program339);
                    interpreter_command8=interpreter_command();

                    state._fsp--;

                    stream_interpreter_command.add(interpreter_command8.getTree());
                    // ExploreGrammar.g:53:28: ( WS )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==WS) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ExploreGrammar.g:53:28: WS
                            {
                            WS9=(Token)match(input,WS,FOLLOW_WS_in_program341);  
                            stream_WS.add(WS9);


                            }
                            break;

                    }

                    EOF10=(Token)match(input,EOF,FOLLOW_EOF_in_program344);  
                    stream_EOF.add(EOF10);



                    // AST REWRITE
                    // elements: interpreter_command
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 53:38: -> interpreter_command
                    {
                        adaptor.addChild(root_0, stream_interpreter_command.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "command"
    // ExploreGrammar.g:56:1: command : ( forward_command | reverse_command | forward_props_command | reverse_props_command | forward_star_command | reverse_star_command | eval_command | all_command | choose_command | lookup_command );
    public final ExploreGrammarParser.command_return command() throws RecognitionException {
        ExploreGrammarParser.command_return retval = new ExploreGrammarParser.command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.forward_command_return forward_command11 = null;

        ExploreGrammarParser.reverse_command_return reverse_command12 = null;

        ExploreGrammarParser.forward_props_command_return forward_props_command13 = null;

        ExploreGrammarParser.reverse_props_command_return reverse_props_command14 = null;

        ExploreGrammarParser.forward_star_command_return forward_star_command15 = null;

        ExploreGrammarParser.reverse_star_command_return reverse_star_command16 = null;

        ExploreGrammarParser.eval_command_return eval_command17 = null;

        ExploreGrammarParser.all_command_return all_command18 = null;

        ExploreGrammarParser.choose_command_return choose_command19 = null;

        ExploreGrammarParser.lookup_command_return lookup_command20 = null;



        try {
            // ExploreGrammar.g:57:2: ( forward_command | reverse_command | forward_props_command | reverse_props_command | forward_star_command | reverse_star_command | eval_command | all_command | choose_command | lookup_command )
            int alt7=10;
            switch ( input.LA(1) ) {
            case FORWARD:
                {
                alt7=1;
                }
                break;
            case REVERSE:
                {
                alt7=2;
                }
                break;
            case FORWARDPROPS:
                {
                alt7=3;
                }
                break;
            case REVERSEPROPS:
                {
                alt7=4;
                }
                break;
            case FORWARDSTAR:
                {
                alt7=5;
                }
                break;
            case REVERSESTAR:
                {
                alt7=6;
                }
                break;
            case EVAL:
                {
                alt7=7;
                }
                break;
            case ALL:
                {
                alt7=8;
                }
                break;
            case CHOOSE:
                {
                alt7=9;
                }
                break;
            case LOOKUP:
                {
                alt7=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ExploreGrammar.g:57:4: forward_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_command_in_command363);
                    forward_command11=forward_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_command11.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:58:4: reverse_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_command_in_command368);
                    reverse_command12=reverse_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_command12.getTree());

                    }
                    break;
                case 3 :
                    // ExploreGrammar.g:59:4: forward_props_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_props_command_in_command373);
                    forward_props_command13=forward_props_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_props_command13.getTree());

                    }
                    break;
                case 4 :
                    // ExploreGrammar.g:60:4: reverse_props_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_props_command_in_command378);
                    reverse_props_command14=reverse_props_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_props_command14.getTree());

                    }
                    break;
                case 5 :
                    // ExploreGrammar.g:61:4: forward_star_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_star_command_in_command383);
                    forward_star_command15=forward_star_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_star_command15.getTree());

                    }
                    break;
                case 6 :
                    // ExploreGrammar.g:62:4: reverse_star_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_star_command_in_command388);
                    reverse_star_command16=reverse_star_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_star_command16.getTree());

                    }
                    break;
                case 7 :
                    // ExploreGrammar.g:63:4: eval_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_eval_command_in_command393);
                    eval_command17=eval_command();

                    state._fsp--;

                    adaptor.addChild(root_0, eval_command17.getTree());

                    }
                    break;
                case 8 :
                    // ExploreGrammar.g:64:4: all_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_all_command_in_command398);
                    all_command18=all_command();

                    state._fsp--;

                    adaptor.addChild(root_0, all_command18.getTree());

                    }
                    break;
                case 9 :
                    // ExploreGrammar.g:65:4: choose_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_choose_command_in_command403);
                    choose_command19=choose_command();

                    state._fsp--;

                    adaptor.addChild(root_0, choose_command19.getTree());

                    }
                    break;
                case 10 :
                    // ExploreGrammar.g:66:4: lookup_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_command_in_command408);
                    lookup_command20=lookup_command();

                    state._fsp--;

                    adaptor.addChild(root_0, lookup_command20.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "command"

    public static class interpreter_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interpreter_command"
    // ExploreGrammar.g:69:1: interpreter_command : ( list_command | pretty_print_command | define_command | size_command | prefix_command | clear_command );
    public final ExploreGrammarParser.interpreter_command_return interpreter_command() throws RecognitionException {
        ExploreGrammarParser.interpreter_command_return retval = new ExploreGrammarParser.interpreter_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.list_command_return list_command21 = null;

        ExploreGrammarParser.pretty_print_command_return pretty_print_command22 = null;

        ExploreGrammarParser.define_command_return define_command23 = null;

        ExploreGrammarParser.size_command_return size_command24 = null;

        ExploreGrammarParser.prefix_command_return prefix_command25 = null;

        ExploreGrammarParser.clear_command_return clear_command26 = null;



        try {
            // ExploreGrammar.g:70:2: ( list_command | pretty_print_command | define_command | size_command | prefix_command | clear_command )
            int alt8=6;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==SLASH) ) {
                switch ( input.LA(2) ) {
                case LIST:
                    {
                    alt8=1;
                    }
                    break;
                case PRETTYPRINT:
                    {
                    alt8=2;
                    }
                    break;
                case DEFINE:
                    {
                    alt8=3;
                    }
                    break;
                case SIZE:
                    {
                    alt8=4;
                    }
                    break;
                case PREFIX:
                    {
                    alt8=5;
                    }
                    break;
                case CLEAR:
                    {
                    alt8=6;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ExploreGrammar.g:70:4: list_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_list_command_in_interpreter_command422);
                    list_command21=list_command();

                    state._fsp--;

                    adaptor.addChild(root_0, list_command21.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:71:4: pretty_print_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pretty_print_command_in_interpreter_command428);
                    pretty_print_command22=pretty_print_command();

                    state._fsp--;

                    adaptor.addChild(root_0, pretty_print_command22.getTree());

                    }
                    break;
                case 3 :
                    // ExploreGrammar.g:72:4: define_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_define_command_in_interpreter_command433);
                    define_command23=define_command();

                    state._fsp--;

                    adaptor.addChild(root_0, define_command23.getTree());

                    }
                    break;
                case 4 :
                    // ExploreGrammar.g:73:4: size_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_size_command_in_interpreter_command438);
                    size_command24=size_command();

                    state._fsp--;

                    adaptor.addChild(root_0, size_command24.getTree());

                    }
                    break;
                case 5 :
                    // ExploreGrammar.g:74:4: prefix_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_prefix_command_in_interpreter_command443);
                    prefix_command25=prefix_command();

                    state._fsp--;

                    adaptor.addChild(root_0, prefix_command25.getTree());

                    }
                    break;
                case 6 :
                    // ExploreGrammar.g:75:4: clear_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_clear_command_in_interpreter_command448);
                    clear_command26=clear_command();

                    state._fsp--;

                    adaptor.addChild(root_0, clear_command26.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "interpreter_command"

    public static class forward_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forward_command"
    // ExploreGrammar.g:78:1: forward_command : FORWARD WS name -> ^( FORWARD name ) ;
    public final ExploreGrammarParser.forward_command_return forward_command() throws RecognitionException {
        ExploreGrammarParser.forward_command_return retval = new ExploreGrammarParser.forward_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARD27=null;
        Token WS28=null;
        ExploreGrammarParser.name_return name29 = null;


        Object FORWARD27_tree=null;
        Object WS28_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_FORWARD=new RewriteRuleTokenStream(adaptor,"token FORWARD");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:78:17: ( FORWARD WS name -> ^( FORWARD name ) )
            // ExploreGrammar.g:78:19: FORWARD WS name
            {
            FORWARD27=(Token)match(input,FORWARD,FOLLOW_FORWARD_in_forward_command462);  
            stream_FORWARD.add(FORWARD27);

            WS28=(Token)match(input,WS,FOLLOW_WS_in_forward_command464);  
            stream_WS.add(WS28);

            pushFollow(FOLLOW_name_in_forward_command466);
            name29=name();

            state._fsp--;

            stream_name.add(name29.getTree());


            // AST REWRITE
            // elements: FORWARD, name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 78:38: -> ^( FORWARD name )
            {
                // ExploreGrammar.g:78:41: ^( FORWARD name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_FORWARD.nextNode(), root_1);

                adaptor.addChild(root_1, stream_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "forward_command"

    public static class reverse_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "reverse_command"
    // ExploreGrammar.g:79:1: reverse_command : REVERSE WS name -> ^( REVERSE name ) ;
    public final ExploreGrammarParser.reverse_command_return reverse_command() throws RecognitionException {
        ExploreGrammarParser.reverse_command_return retval = new ExploreGrammarParser.reverse_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSE30=null;
        Token WS31=null;
        ExploreGrammarParser.name_return name32 = null;


        Object REVERSE30_tree=null;
        Object WS31_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_REVERSE=new RewriteRuleTokenStream(adaptor,"token REVERSE");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:79:17: ( REVERSE WS name -> ^( REVERSE name ) )
            // ExploreGrammar.g:79:19: REVERSE WS name
            {
            REVERSE30=(Token)match(input,REVERSE,FOLLOW_REVERSE_in_reverse_command485);  
            stream_REVERSE.add(REVERSE30);

            WS31=(Token)match(input,WS,FOLLOW_WS_in_reverse_command487);  
            stream_WS.add(WS31);

            pushFollow(FOLLOW_name_in_reverse_command489);
            name32=name();

            state._fsp--;

            stream_name.add(name32.getTree());


            // AST REWRITE
            // elements: REVERSE, name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 79:38: -> ^( REVERSE name )
            {
                // ExploreGrammar.g:79:41: ^( REVERSE name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_REVERSE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reverse_command"

    public static class forward_star_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forward_star_command"
    // ExploreGrammar.g:80:1: forward_star_command : FORWARDSTAR WS name -> ^( FORWARDSTAR name ) ;
    public final ExploreGrammarParser.forward_star_command_return forward_star_command() throws RecognitionException {
        ExploreGrammarParser.forward_star_command_return retval = new ExploreGrammarParser.forward_star_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARDSTAR33=null;
        Token WS34=null;
        ExploreGrammarParser.name_return name35 = null;


        Object FORWARDSTAR33_tree=null;
        Object WS34_tree=null;
        RewriteRuleTokenStream stream_FORWARDSTAR=new RewriteRuleTokenStream(adaptor,"token FORWARDSTAR");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:80:22: ( FORWARDSTAR WS name -> ^( FORWARDSTAR name ) )
            // ExploreGrammar.g:80:24: FORWARDSTAR WS name
            {
            FORWARDSTAR33=(Token)match(input,FORWARDSTAR,FOLLOW_FORWARDSTAR_in_forward_star_command509);  
            stream_FORWARDSTAR.add(FORWARDSTAR33);

            WS34=(Token)match(input,WS,FOLLOW_WS_in_forward_star_command511);  
            stream_WS.add(WS34);

            pushFollow(FOLLOW_name_in_forward_star_command513);
            name35=name();

            state._fsp--;

            stream_name.add(name35.getTree());


            // AST REWRITE
            // elements: name, FORWARDSTAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 80:45: -> ^( FORWARDSTAR name )
            {
                // ExploreGrammar.g:80:48: ^( FORWARDSTAR name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_FORWARDSTAR.nextNode(), root_1);

                adaptor.addChild(root_1, stream_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "forward_star_command"

    public static class reverse_star_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "reverse_star_command"
    // ExploreGrammar.g:81:1: reverse_star_command : REVERSESTAR WS name -> ^( REVERSESTAR name ) ;
    public final ExploreGrammarParser.reverse_star_command_return reverse_star_command() throws RecognitionException {
        ExploreGrammarParser.reverse_star_command_return retval = new ExploreGrammarParser.reverse_star_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSESTAR36=null;
        Token WS37=null;
        ExploreGrammarParser.name_return name38 = null;


        Object REVERSESTAR36_tree=null;
        Object WS37_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_REVERSESTAR=new RewriteRuleTokenStream(adaptor,"token REVERSESTAR");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:81:22: ( REVERSESTAR WS name -> ^( REVERSESTAR name ) )
            // ExploreGrammar.g:81:24: REVERSESTAR WS name
            {
            REVERSESTAR36=(Token)match(input,REVERSESTAR,FOLLOW_REVERSESTAR_in_reverse_star_command530);  
            stream_REVERSESTAR.add(REVERSESTAR36);

            WS37=(Token)match(input,WS,FOLLOW_WS_in_reverse_star_command532);  
            stream_WS.add(WS37);

            pushFollow(FOLLOW_name_in_reverse_star_command534);
            name38=name();

            state._fsp--;

            stream_name.add(name38.getTree());


            // AST REWRITE
            // elements: REVERSESTAR, name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 81:45: -> ^( REVERSESTAR name )
            {
                // ExploreGrammar.g:81:48: ^( REVERSESTAR name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_REVERSESTAR.nextNode(), root_1);

                adaptor.addChild(root_1, stream_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reverse_star_command"

    public static class forward_props_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forward_props_command"
    // ExploreGrammar.g:82:1: forward_props_command : FORWARDPROPS -> ^( FORWARDPROPS ) ;
    public final ExploreGrammarParser.forward_props_command_return forward_props_command() throws RecognitionException {
        ExploreGrammarParser.forward_props_command_return retval = new ExploreGrammarParser.forward_props_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARDPROPS39=null;

        Object FORWARDPROPS39_tree=null;
        RewriteRuleTokenStream stream_FORWARDPROPS=new RewriteRuleTokenStream(adaptor,"token FORWARDPROPS");

        try {
            // ExploreGrammar.g:82:23: ( FORWARDPROPS -> ^( FORWARDPROPS ) )
            // ExploreGrammar.g:82:25: FORWARDPROPS
            {
            FORWARDPROPS39=(Token)match(input,FORWARDPROPS,FOLLOW_FORWARDPROPS_in_forward_props_command552);  
            stream_FORWARDPROPS.add(FORWARDPROPS39);



            // AST REWRITE
            // elements: FORWARDPROPS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 82:40: -> ^( FORWARDPROPS )
            {
                // ExploreGrammar.g:82:43: ^( FORWARDPROPS )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_FORWARDPROPS.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "forward_props_command"

    public static class reverse_props_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "reverse_props_command"
    // ExploreGrammar.g:83:1: reverse_props_command : REVERSEPROPS -> ^( REVERSEPROPS ) ;
    public final ExploreGrammarParser.reverse_props_command_return reverse_props_command() throws RecognitionException {
        ExploreGrammarParser.reverse_props_command_return retval = new ExploreGrammarParser.reverse_props_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSEPROPS40=null;

        Object REVERSEPROPS40_tree=null;
        RewriteRuleTokenStream stream_REVERSEPROPS=new RewriteRuleTokenStream(adaptor,"token REVERSEPROPS");

        try {
            // ExploreGrammar.g:83:23: ( REVERSEPROPS -> ^( REVERSEPROPS ) )
            // ExploreGrammar.g:83:25: REVERSEPROPS
            {
            REVERSEPROPS40=(Token)match(input,REVERSEPROPS,FOLLOW_REVERSEPROPS_in_reverse_props_command569);  
            stream_REVERSEPROPS.add(REVERSEPROPS40);



            // AST REWRITE
            // elements: REVERSEPROPS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 83:40: -> ^( REVERSEPROPS )
            {
                // ExploreGrammar.g:83:43: ^( REVERSEPROPS )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_REVERSEPROPS.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reverse_props_command"

    public static class lookup_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lookup_command"
    // ExploreGrammar.g:85:1: lookup_command : LOOKUP WS typed_literal -> ^( LOOKUP typed_literal ) ;
    public final ExploreGrammarParser.lookup_command_return lookup_command() throws RecognitionException {
        ExploreGrammarParser.lookup_command_return retval = new ExploreGrammarParser.lookup_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LOOKUP41=null;
        Token WS42=null;
        ExploreGrammarParser.typed_literal_return typed_literal43 = null;


        Object LOOKUP41_tree=null;
        Object WS42_tree=null;
        RewriteRuleTokenStream stream_LOOKUP=new RewriteRuleTokenStream(adaptor,"token LOOKUP");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleSubtreeStream stream_typed_literal=new RewriteRuleSubtreeStream(adaptor,"rule typed_literal");
        try {
            // ExploreGrammar.g:85:16: ( LOOKUP WS typed_literal -> ^( LOOKUP typed_literal ) )
            // ExploreGrammar.g:85:18: LOOKUP WS typed_literal
            {
            LOOKUP41=(Token)match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup_command587);  
            stream_LOOKUP.add(LOOKUP41);

            WS42=(Token)match(input,WS,FOLLOW_WS_in_lookup_command589);  
            stream_WS.add(WS42);

            pushFollow(FOLLOW_typed_literal_in_lookup_command591);
            typed_literal43=typed_literal();

            state._fsp--;

            stream_typed_literal.add(typed_literal43.getTree());


            // AST REWRITE
            // elements: typed_literal, LOOKUP
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 85:43: -> ^( LOOKUP typed_literal )
            {
                // ExploreGrammar.g:85:46: ^( LOOKUP typed_literal )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_LOOKUP.nextNode(), root_1);

                adaptor.addChild(root_1, stream_typed_literal.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lookup_command"

    public static class eval_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eval_command"
    // ExploreGrammar.g:86:1: eval_command : EVAL WS name -> ^( EVAL name ) ;
    public final ExploreGrammarParser.eval_command_return eval_command() throws RecognitionException {
        ExploreGrammarParser.eval_command_return retval = new ExploreGrammarParser.eval_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EVAL44=null;
        Token WS45=null;
        ExploreGrammarParser.name_return name46 = null;


        Object EVAL44_tree=null;
        Object WS45_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_EVAL=new RewriteRuleTokenStream(adaptor,"token EVAL");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:86:14: ( EVAL WS name -> ^( EVAL name ) )
            // ExploreGrammar.g:86:16: EVAL WS name
            {
            EVAL44=(Token)match(input,EVAL,FOLLOW_EVAL_in_eval_command609);  
            stream_EVAL.add(EVAL44);

            WS45=(Token)match(input,WS,FOLLOW_WS_in_eval_command611);  
            stream_WS.add(WS45);

            pushFollow(FOLLOW_name_in_eval_command613);
            name46=name();

            state._fsp--;

            stream_name.add(name46.getTree());


            // AST REWRITE
            // elements: name, EVAL
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 86:33: -> ^( EVAL name )
            {
                // ExploreGrammar.g:86:36: ^( EVAL name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_EVAL.nextNode(), root_1);

                adaptor.addChild(root_1, stream_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "eval_command"

    public static class all_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "all_command"
    // ExploreGrammar.g:87:1: all_command : ALL -> ^( ALL ) ;
    public final ExploreGrammarParser.all_command_return all_command() throws RecognitionException {
        ExploreGrammarParser.all_command_return retval = new ExploreGrammarParser.all_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL47=null;

        Object ALL47_tree=null;
        RewriteRuleTokenStream stream_ALL=new RewriteRuleTokenStream(adaptor,"token ALL");

        try {
            // ExploreGrammar.g:87:13: ( ALL -> ^( ALL ) )
            // ExploreGrammar.g:87:15: ALL
            {
            ALL47=(Token)match(input,ALL,FOLLOW_ALL_in_all_command633);  
            stream_ALL.add(ALL47);



            // AST REWRITE
            // elements: ALL
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 87:26: -> ^( ALL )
            {
                // ExploreGrammar.g:87:29: ^( ALL )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_ALL.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "all_command"

    public static class choose_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "choose_command"
    // ExploreGrammar.g:88:1: choose_command : CHOOSE -> ^( CHOOSE ) ;
    public final ExploreGrammarParser.choose_command_return choose_command() throws RecognitionException {
        ExploreGrammarParser.choose_command_return retval = new ExploreGrammarParser.choose_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHOOSE48=null;

        Object CHOOSE48_tree=null;
        RewriteRuleTokenStream stream_CHOOSE=new RewriteRuleTokenStream(adaptor,"token CHOOSE");

        try {
            // ExploreGrammar.g:88:16: ( CHOOSE -> ^( CHOOSE ) )
            // ExploreGrammar.g:88:18: CHOOSE
            {
            CHOOSE48=(Token)match(input,CHOOSE,FOLLOW_CHOOSE_in_choose_command654);  
            stream_CHOOSE.add(CHOOSE48);



            // AST REWRITE
            // elements: CHOOSE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 88:30: -> ^( CHOOSE )
            {
                // ExploreGrammar.g:88:33: ^( CHOOSE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_CHOOSE.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "choose_command"

    public static class define_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "define_command"
    // ExploreGrammar.g:90:1: define_command : SLASH DEFINE WS VARIABLE -> ^( DEFINE VARIABLE ) ;
    public final ExploreGrammarParser.define_command_return define_command() throws RecognitionException {
        ExploreGrammarParser.define_command_return retval = new ExploreGrammarParser.define_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH49=null;
        Token DEFINE50=null;
        Token WS51=null;
        Token VARIABLE52=null;

        Object SLASH49_tree=null;
        Object DEFINE50_tree=null;
        Object WS51_tree=null;
        Object VARIABLE52_tree=null;
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
        RewriteRuleTokenStream stream_DEFINE=new RewriteRuleTokenStream(adaptor,"token DEFINE");

        try {
            // ExploreGrammar.g:90:16: ( SLASH DEFINE WS VARIABLE -> ^( DEFINE VARIABLE ) )
            // ExploreGrammar.g:90:19: SLASH DEFINE WS VARIABLE
            {
            SLASH49=(Token)match(input,SLASH,FOLLOW_SLASH_in_define_command675);  
            stream_SLASH.add(SLASH49);

            DEFINE50=(Token)match(input,DEFINE,FOLLOW_DEFINE_in_define_command677);  
            stream_DEFINE.add(DEFINE50);

            WS51=(Token)match(input,WS,FOLLOW_WS_in_define_command679);  
            stream_WS.add(WS51);

            VARIABLE52=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_define_command681);  
            stream_VARIABLE.add(VARIABLE52);



            // AST REWRITE
            // elements: VARIABLE, DEFINE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 90:46: -> ^( DEFINE VARIABLE )
            {
                // ExploreGrammar.g:90:49: ^( DEFINE VARIABLE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_DEFINE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_VARIABLE.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "define_command"

    public static class list_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_command"
    // ExploreGrammar.g:91:1: list_command : SLASH LIST -> ^( LIST ) ;
    public final ExploreGrammarParser.list_command_return list_command() throws RecognitionException {
        ExploreGrammarParser.list_command_return retval = new ExploreGrammarParser.list_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH53=null;
        Token LIST54=null;

        Object SLASH53_tree=null;
        Object LIST54_tree=null;
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_LIST=new RewriteRuleTokenStream(adaptor,"token LIST");

        try {
            // ExploreGrammar.g:91:14: ( SLASH LIST -> ^( LIST ) )
            // ExploreGrammar.g:91:17: SLASH LIST
            {
            SLASH53=(Token)match(input,SLASH,FOLLOW_SLASH_in_list_command701);  
            stream_SLASH.add(SLASH53);

            LIST54=(Token)match(input,LIST,FOLLOW_LIST_in_list_command703);  
            stream_LIST.add(LIST54);



            // AST REWRITE
            // elements: LIST
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 91:34: -> ^( LIST )
            {
                // ExploreGrammar.g:91:37: ^( LIST )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_LIST.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_command"

    public static class pretty_print_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pretty_print_command"
    // ExploreGrammar.g:92:1: pretty_print_command : SLASH PRETTYPRINT -> ^( PRETTYPRINT ) ;
    public final ExploreGrammarParser.pretty_print_command_return pretty_print_command() throws RecognitionException {
        ExploreGrammarParser.pretty_print_command_return retval = new ExploreGrammarParser.pretty_print_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH55=null;
        Token PRETTYPRINT56=null;

        Object SLASH55_tree=null;
        Object PRETTYPRINT56_tree=null;
        RewriteRuleTokenStream stream_PRETTYPRINT=new RewriteRuleTokenStream(adaptor,"token PRETTYPRINT");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");

        try {
            // ExploreGrammar.g:92:22: ( SLASH PRETTYPRINT -> ^( PRETTYPRINT ) )
            // ExploreGrammar.g:92:25: SLASH PRETTYPRINT
            {
            SLASH55=(Token)match(input,SLASH,FOLLOW_SLASH_in_pretty_print_command724);  
            stream_SLASH.add(SLASH55);

            PRETTYPRINT56=(Token)match(input,PRETTYPRINT,FOLLOW_PRETTYPRINT_in_pretty_print_command726);  
            stream_PRETTYPRINT.add(PRETTYPRINT56);



            // AST REWRITE
            // elements: PRETTYPRINT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 92:45: -> ^( PRETTYPRINT )
            {
                // ExploreGrammar.g:92:48: ^( PRETTYPRINT )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_PRETTYPRINT.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pretty_print_command"

    public static class size_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "size_command"
    // ExploreGrammar.g:93:1: size_command : SLASH SIZE -> ^( SIZE ) ;
    public final ExploreGrammarParser.size_command_return size_command() throws RecognitionException {
        ExploreGrammarParser.size_command_return retval = new ExploreGrammarParser.size_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH57=null;
        Token SIZE58=null;

        Object SLASH57_tree=null;
        Object SIZE58_tree=null;
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_SIZE=new RewriteRuleTokenStream(adaptor,"token SIZE");

        try {
            // ExploreGrammar.g:93:14: ( SLASH SIZE -> ^( SIZE ) )
            // ExploreGrammar.g:93:17: SLASH SIZE
            {
            SLASH57=(Token)match(input,SLASH,FOLLOW_SLASH_in_size_command743);  
            stream_SLASH.add(SLASH57);

            SIZE58=(Token)match(input,SIZE,FOLLOW_SIZE_in_size_command745);  
            stream_SIZE.add(SIZE58);



            // AST REWRITE
            // elements: SIZE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 93:34: -> ^( SIZE )
            {
                // ExploreGrammar.g:93:37: ^( SIZE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_SIZE.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "size_command"

    public static class prefix_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "prefix_command"
    // ExploreGrammar.g:94:1: prefix_command : SLASH PREFIX WS KEYWORD WS IRI -> ^( PREFIX KEYWORD IRI ) ;
    public final ExploreGrammarParser.prefix_command_return prefix_command() throws RecognitionException {
        ExploreGrammarParser.prefix_command_return retval = new ExploreGrammarParser.prefix_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH59=null;
        Token PREFIX60=null;
        Token WS61=null;
        Token KEYWORD62=null;
        Token WS63=null;
        Token IRI64=null;

        Object SLASH59_tree=null;
        Object PREFIX60_tree=null;
        Object WS61_tree=null;
        Object KEYWORD62_tree=null;
        Object WS63_tree=null;
        Object IRI64_tree=null;
        RewriteRuleTokenStream stream_PREFIX=new RewriteRuleTokenStream(adaptor,"token PREFIX");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_KEYWORD=new RewriteRuleTokenStream(adaptor,"token KEYWORD");
        RewriteRuleTokenStream stream_IRI=new RewriteRuleTokenStream(adaptor,"token IRI");

        try {
            // ExploreGrammar.g:94:16: ( SLASH PREFIX WS KEYWORD WS IRI -> ^( PREFIX KEYWORD IRI ) )
            // ExploreGrammar.g:94:18: SLASH PREFIX WS KEYWORD WS IRI
            {
            SLASH59=(Token)match(input,SLASH,FOLLOW_SLASH_in_prefix_command765);  
            stream_SLASH.add(SLASH59);

            PREFIX60=(Token)match(input,PREFIX,FOLLOW_PREFIX_in_prefix_command767);  
            stream_PREFIX.add(PREFIX60);

            WS61=(Token)match(input,WS,FOLLOW_WS_in_prefix_command769);  
            stream_WS.add(WS61);

            KEYWORD62=(Token)match(input,KEYWORD,FOLLOW_KEYWORD_in_prefix_command771);  
            stream_KEYWORD.add(KEYWORD62);

            WS63=(Token)match(input,WS,FOLLOW_WS_in_prefix_command773);  
            stream_WS.add(WS63);

            IRI64=(Token)match(input,IRI,FOLLOW_IRI_in_prefix_command775);  
            stream_IRI.add(IRI64);



            // AST REWRITE
            // elements: KEYWORD, IRI, PREFIX
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 94:49: -> ^( PREFIX KEYWORD IRI )
            {
                // ExploreGrammar.g:94:52: ^( PREFIX KEYWORD IRI )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_PREFIX.nextNode(), root_1);

                adaptor.addChild(root_1, stream_KEYWORD.nextNode());
                adaptor.addChild(root_1, stream_IRI.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "prefix_command"

    public static class clear_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "clear_command"
    // ExploreGrammar.g:95:1: clear_command : SLASH CLEAR -> ^( CLEAR ) ;
    public final ExploreGrammarParser.clear_command_return clear_command() throws RecognitionException {
        ExploreGrammarParser.clear_command_return retval = new ExploreGrammarParser.clear_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH65=null;
        Token CLEAR66=null;

        Object SLASH65_tree=null;
        Object CLEAR66_tree=null;
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_CLEAR=new RewriteRuleTokenStream(adaptor,"token CLEAR");

        try {
            // ExploreGrammar.g:95:15: ( SLASH CLEAR -> ^( CLEAR ) )
            // ExploreGrammar.g:95:17: SLASH CLEAR
            {
            SLASH65=(Token)match(input,SLASH,FOLLOW_SLASH_in_clear_command793);  
            stream_SLASH.add(SLASH65);

            CLEAR66=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_clear_command795);  
            stream_CLEAR.add(CLEAR66);



            // AST REWRITE
            // elements: CLEAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 95:34: -> ^( CLEAR )
            {
                // ExploreGrammar.g:95:37: ^( CLEAR )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_CLEAR.nextNode(), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "clear_command"

    public static class custom_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "custom_command"
    // ExploreGrammar.g:97:1: custom_command : KEYWORD ( WS value )* -> ^( KEYWORD ( value )* ) ;
    public final ExploreGrammarParser.custom_command_return custom_command() throws RecognitionException {
        ExploreGrammarParser.custom_command_return retval = new ExploreGrammarParser.custom_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KEYWORD67=null;
        Token WS68=null;
        ExploreGrammarParser.value_return value69 = null;


        Object KEYWORD67_tree=null;
        Object WS68_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_KEYWORD=new RewriteRuleTokenStream(adaptor,"token KEYWORD");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // ExploreGrammar.g:97:16: ( KEYWORD ( WS value )* -> ^( KEYWORD ( value )* ) )
            // ExploreGrammar.g:97:18: KEYWORD ( WS value )*
            {
            KEYWORD67=(Token)match(input,KEYWORD,FOLLOW_KEYWORD_in_custom_command817);  
            stream_KEYWORD.add(KEYWORD67);

            // ExploreGrammar.g:97:26: ( WS value )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==WS) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ExploreGrammar.g:97:27: WS value
            	    {
            	    WS68=(Token)match(input,WS,FOLLOW_WS_in_custom_command820);  
            	    stream_WS.add(WS68);

            	    pushFollow(FOLLOW_value_in_custom_command822);
            	    value69=value();

            	    state._fsp--;

            	    stream_value.add(value69.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);



            // AST REWRITE
            // elements: value, KEYWORD
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 97:41: -> ^( KEYWORD ( value )* )
            {
                // ExploreGrammar.g:97:44: ^( KEYWORD ( value )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_KEYWORD.nextNode(), root_1);

                // ExploreGrammar.g:97:55: ( value )*
                while ( stream_value.hasNext() ) {
                    adaptor.addChild(root_1, stream_value.nextTree());

                }
                stream_value.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "custom_command"

    public static class eol_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eol"
    // ExploreGrammar.g:99:1: eol : ( ( WS )? NEWLINE )+ ;
    public final ExploreGrammarParser.eol_return eol() throws RecognitionException {
        ExploreGrammarParser.eol_return retval = new ExploreGrammarParser.eol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS70=null;
        Token NEWLINE71=null;

        Object WS70_tree=null;
        Object NEWLINE71_tree=null;

        try {
            // ExploreGrammar.g:99:5: ( ( ( WS )? NEWLINE )+ )
            // ExploreGrammar.g:99:7: ( ( WS )? NEWLINE )+
            {
            root_0 = (Object)adaptor.nil();

            // ExploreGrammar.g:99:7: ( ( WS )? NEWLINE )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==WS||LA11_0==NEWLINE) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ExploreGrammar.g:99:8: ( WS )? NEWLINE
            	    {
            	    // ExploreGrammar.g:99:8: ( WS )?
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==WS) ) {
            	        alt10=1;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // ExploreGrammar.g:99:8: WS
            	            {
            	            WS70=(Token)match(input,WS,FOLLOW_WS_in_eol848); 
            	            WS70_tree = (Object)adaptor.create(WS70);
            	            adaptor.addChild(root_0, WS70_tree);


            	            }
            	            break;

            	    }

            	    NEWLINE71=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eol851); 
            	    NEWLINE71_tree = (Object)adaptor.create(NEWLINE71);
            	    adaptor.addChild(root_0, NEWLINE71_tree);


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "eol"

    public static class value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "value"
    // ExploreGrammar.g:101:1: value : ( name | typed_literal );
    public final ExploreGrammarParser.value_return value() throws RecognitionException {
        ExploreGrammarParser.value_return retval = new ExploreGrammarParser.value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.name_return name72 = null;

        ExploreGrammarParser.typed_literal_return typed_literal73 = null;



        try {
            // ExploreGrammar.g:102:2: ( name | typed_literal )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==VARIABLE||LA12_0==IRI||LA12_0==PREFIXED) ) {
                alt12=1;
            }
            else if ( (LA12_0==LITERAL) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ExploreGrammar.g:102:4: name
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_name_in_value864);
                    name72=name();

                    state._fsp--;

                    adaptor.addChild(root_0, name72.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:103:4: typed_literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_typed_literal_in_value869);
                    typed_literal73=typed_literal();

                    state._fsp--;

                    adaptor.addChild(root_0, typed_literal73.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "value"

    public static class name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "name"
    // ExploreGrammar.g:106:1: name : ( resource | VARIABLE );
    public final ExploreGrammarParser.name_return name() throws RecognitionException {
        ExploreGrammarParser.name_return retval = new ExploreGrammarParser.name_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VARIABLE75=null;
        ExploreGrammarParser.resource_return resource74 = null;


        Object VARIABLE75_tree=null;

        try {
            // ExploreGrammar.g:107:2: ( resource | VARIABLE )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IRI||LA13_0==PREFIXED) ) {
                alt13=1;
            }
            else if ( (LA13_0==VARIABLE) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ExploreGrammar.g:107:4: resource
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_resource_in_name884);
                    resource74=resource();

                    state._fsp--;

                    adaptor.addChild(root_0, resource74.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:108:4: VARIABLE
                    {
                    root_0 = (Object)adaptor.nil();

                    VARIABLE75=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_name889); 
                    VARIABLE75_tree = (Object)adaptor.create(VARIABLE75);
                    adaptor.addChild(root_0, VARIABLE75_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "name"

    public static class resource_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "resource"
    // ExploreGrammar.g:111:1: resource : ( PREFIXED | IRI );
    public final ExploreGrammarParser.resource_return resource() throws RecognitionException {
        ExploreGrammarParser.resource_return retval = new ExploreGrammarParser.resource_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set76=null;

        Object set76_tree=null;

        try {
            // ExploreGrammar.g:112:2: ( PREFIXED | IRI )
            // ExploreGrammar.g:
            {
            root_0 = (Object)adaptor.nil();

            set76=(Token)input.LT(1);
            if ( input.LA(1)==IRI||input.LA(1)==PREFIXED ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set76));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "resource"

    public static class typed_literal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typed_literal"
    // ExploreGrammar.g:116:1: typed_literal : ( LITERAL -> ^( TYPEDLITERAL LITERAL BLANKTYPE ) | LITERAL TYPED resource -> ^( TYPEDLITERAL LITERAL resource ) );
    public final ExploreGrammarParser.typed_literal_return typed_literal() throws RecognitionException {
        ExploreGrammarParser.typed_literal_return retval = new ExploreGrammarParser.typed_literal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LITERAL77=null;
        Token LITERAL78=null;
        Token TYPED79=null;
        ExploreGrammarParser.resource_return resource80 = null;


        Object LITERAL77_tree=null;
        Object LITERAL78_tree=null;
        Object TYPED79_tree=null;
        RewriteRuleTokenStream stream_TYPED=new RewriteRuleTokenStream(adaptor,"token TYPED");
        RewriteRuleTokenStream stream_LITERAL=new RewriteRuleTokenStream(adaptor,"token LITERAL");
        RewriteRuleSubtreeStream stream_resource=new RewriteRuleSubtreeStream(adaptor,"rule resource");
        try {
            // ExploreGrammar.g:117:2: ( LITERAL -> ^( TYPEDLITERAL LITERAL BLANKTYPE ) | LITERAL TYPED resource -> ^( TYPEDLITERAL LITERAL resource ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==LITERAL) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==TYPED) ) {
                    alt14=2;
                }
                else if ( (LA14_1==EOF||LA14_1==WS) ) {
                    alt14=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ExploreGrammar.g:117:4: LITERAL
                    {
                    LITERAL77=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_typed_literal923);  
                    stream_LITERAL.add(LITERAL77);



                    // AST REWRITE
                    // elements: LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 117:13: -> ^( TYPEDLITERAL LITERAL BLANKTYPE )
                    {
                        // ExploreGrammar.g:117:16: ^( TYPEDLITERAL LITERAL BLANKTYPE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPEDLITERAL, "TYPEDLITERAL"), root_1);

                        adaptor.addChild(root_1, stream_LITERAL.nextNode());
                        adaptor.addChild(root_1, (Object)adaptor.create(BLANKTYPE, "BLANKTYPE"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:118:4: LITERAL TYPED resource
                    {
                    LITERAL78=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_typed_literal940);  
                    stream_LITERAL.add(LITERAL78);

                    TYPED79=(Token)match(input,TYPED,FOLLOW_TYPED_in_typed_literal942);  
                    stream_TYPED.add(TYPED79);

                    pushFollow(FOLLOW_resource_in_typed_literal944);
                    resource80=resource();

                    state._fsp--;

                    stream_resource.add(resource80.getTree());


                    // AST REWRITE
                    // elements: resource, LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 118:27: -> ^( TYPEDLITERAL LITERAL resource )
                    {
                        // ExploreGrammar.g:118:30: ^( TYPEDLITERAL LITERAL resource )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPEDLITERAL, "TYPEDLITERAL"), root_1);

                        adaptor.addChild(root_1, stream_LITERAL.nextNode());
                        adaptor.addChild(root_1, stream_resource.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typed_literal"

    // Delegated rules


 

    public static final BitSet FOLLOW_WS_in_program311 = new BitSet(new long[]{0x000000000000FFC0L});
    public static final BitSet FOLLOW_command_in_program314 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_program317 = new BitSet(new long[]{0x000000000000FFC0L});
    public static final BitSet FOLLOW_command_in_program319 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_program323 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_program336 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_interpreter_command_in_program339 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_program341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_command_in_command363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_command_in_command368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_props_command_in_command373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_props_command_in_command378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_star_command_in_command383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_star_command_in_command388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eval_command_in_command393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_all_command_in_command398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_choose_command_in_command403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_command_in_command408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_command_in_interpreter_command422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pretty_print_command_in_interpreter_command428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_command_in_interpreter_command433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_size_command_in_interpreter_command438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefix_command_in_interpreter_command443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clear_command_in_interpreter_command448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARD_in_forward_command462 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_forward_command464 = new BitSet(new long[]{0x0000000540000000L});
    public static final BitSet FOLLOW_name_in_forward_command466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSE_in_reverse_command485 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_reverse_command487 = new BitSet(new long[]{0x0000000540000000L});
    public static final BitSet FOLLOW_name_in_reverse_command489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARDSTAR_in_forward_star_command509 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_forward_star_command511 = new BitSet(new long[]{0x0000000540000000L});
    public static final BitSet FOLLOW_name_in_forward_star_command513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSESTAR_in_reverse_star_command530 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_reverse_star_command532 = new BitSet(new long[]{0x0000000540000000L});
    public static final BitSet FOLLOW_name_in_reverse_star_command534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARDPROPS_in_forward_props_command552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSEPROPS_in_reverse_props_command569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup_command587 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_lookup_command589 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_typed_literal_in_lookup_command591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EVAL_in_eval_command609 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_eval_command611 = new BitSet(new long[]{0x0000000540000000L});
    public static final BitSet FOLLOW_name_in_eval_command613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_all_command633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHOOSE_in_choose_command654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_define_command675 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DEFINE_in_define_command677 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_define_command679 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_VARIABLE_in_define_command681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_list_command701 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_LIST_in_list_command703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_pretty_print_command724 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_PRETTYPRINT_in_pretty_print_command726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_size_command743 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_SIZE_in_size_command745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_prefix_command765 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_PREFIX_in_prefix_command767 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_prefix_command769 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_KEYWORD_in_prefix_command771 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_WS_in_prefix_command773 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_IRI_in_prefix_command775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_clear_command793 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_CLEAR_in_clear_command795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_in_custom_command817 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_WS_in_custom_command820 = new BitSet(new long[]{0x0000000D40000000L});
    public static final BitSet FOLLOW_value_in_custom_command822 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_WS_in_eol848 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_NEWLINE_in_eol851 = new BitSet(new long[]{0x0000000220000002L});
    public static final BitSet FOLLOW_name_in_value864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typed_literal_in_value869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resource_in_name884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_name889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_resource0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_in_typed_literal923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_in_typed_literal940 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_TYPED_in_typed_literal942 = new BitSet(new long[]{0x0000000500000000L});
    public static final BitSet FOLLOW_resource_in_typed_literal944 = new BitSet(new long[]{0x0000000000000002L});

}