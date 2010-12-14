// $ANTLR 3.3 Nov 30, 2010 12:50:56 ExploreGrammar.g 2010-12-13 16:28:41
 
package tdanford.browser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class ExploreGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PREFIX", "FORWARDPROPS", "REVERSEPROPS", "FORWARDSTAR", "REVERSESTAR", "FORWARD", "REVERSE", "EVAL", "LOOKUP", "ALL", "CHOOSE", "PRETTYPRINT", "LIST", "DEFINE", "DOLLAR", "QUOTE", "LANGLE", "RANGLE", "COLON", "TYPED", "SLASH", "SIZE", "BLANKTYPE", "TYPEDLITERAL", "WS", "VARIABLE", "KEYWORD", "IRI", "NEWLINE", "PREFIXED", "LITERAL", "INTEGER", "QUOTED", "LETTER", "DIGIT"
    };
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
    // ExploreGrammar.g:49:1: program : ( WS )? command ( WS command )* ( WS )? EOF -> ( command )+ ;
    public final ExploreGrammarParser.program_return program() throws RecognitionException {
        ExploreGrammarParser.program_return retval = new ExploreGrammarParser.program_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS1=null;
        Token WS3=null;
        Token WS5=null;
        Token EOF6=null;
        ExploreGrammarParser.command_return command2 = null;

        ExploreGrammarParser.command_return command4 = null;


        Object WS1_tree=null;
        Object WS3_tree=null;
        Object WS5_tree=null;
        Object EOF6_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_command=new RewriteRuleSubtreeStream(adaptor,"rule command");
        try {
            // ExploreGrammar.g:49:9: ( ( WS )? command ( WS command )* ( WS )? EOF -> ( command )+ )
            // ExploreGrammar.g:49:11: ( WS )? command ( WS command )* ( WS )? EOF
            {
            // ExploreGrammar.g:49:11: ( WS )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==WS) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ExploreGrammar.g:49:11: WS
                    {
                    WS1=(Token)match(input,WS,FOLLOW_WS_in_program297);  
                    stream_WS.add(WS1);


                    }
                    break;

            }

            pushFollow(FOLLOW_command_in_program300);
            command2=command();

            state._fsp--;

            stream_command.add(command2.getTree());
            // ExploreGrammar.g:49:23: ( WS command )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==WS) ) {
                    int LA2_1 = input.LA(2);

                    if ( ((LA2_1>=PREFIX && LA2_1<=DEFINE)||LA2_1==SIZE) ) {
                        alt2=1;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // ExploreGrammar.g:49:24: WS command
            	    {
            	    WS3=(Token)match(input,WS,FOLLOW_WS_in_program303);  
            	    stream_WS.add(WS3);

            	    pushFollow(FOLLOW_command_in_program305);
            	    command4=command();

            	    state._fsp--;

            	    stream_command.add(command4.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ExploreGrammar.g:49:37: ( WS )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==WS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ExploreGrammar.g:49:37: WS
                    {
                    WS5=(Token)match(input,WS,FOLLOW_WS_in_program309);  
                    stream_WS.add(WS5);


                    }
                    break;

            }

            EOF6=(Token)match(input,EOF,FOLLOW_EOF_in_program312);  
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
            // 49:45: -> ( command )+
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
    // ExploreGrammar.g:51:1: command : ( forward_command | reverse_command | forward_props_command | reverse_props_command | forward_star_command | reverse_star_command | eval_command | all_command | choose_command | lookup_command | interpreter_command );
    public final ExploreGrammarParser.command_return command() throws RecognitionException {
        ExploreGrammarParser.command_return retval = new ExploreGrammarParser.command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.forward_command_return forward_command7 = null;

        ExploreGrammarParser.reverse_command_return reverse_command8 = null;

        ExploreGrammarParser.forward_props_command_return forward_props_command9 = null;

        ExploreGrammarParser.reverse_props_command_return reverse_props_command10 = null;

        ExploreGrammarParser.forward_star_command_return forward_star_command11 = null;

        ExploreGrammarParser.reverse_star_command_return reverse_star_command12 = null;

        ExploreGrammarParser.eval_command_return eval_command13 = null;

        ExploreGrammarParser.all_command_return all_command14 = null;

        ExploreGrammarParser.choose_command_return choose_command15 = null;

        ExploreGrammarParser.lookup_command_return lookup_command16 = null;

        ExploreGrammarParser.interpreter_command_return interpreter_command17 = null;



        try {
            // ExploreGrammar.g:52:2: ( forward_command | reverse_command | forward_props_command | reverse_props_command | forward_star_command | reverse_star_command | eval_command | all_command | choose_command | lookup_command | interpreter_command )
            int alt4=11;
            switch ( input.LA(1) ) {
            case FORWARD:
                {
                alt4=1;
                }
                break;
            case REVERSE:
                {
                alt4=2;
                }
                break;
            case FORWARDPROPS:
                {
                alt4=3;
                }
                break;
            case REVERSEPROPS:
                {
                alt4=4;
                }
                break;
            case FORWARDSTAR:
                {
                alt4=5;
                }
                break;
            case REVERSESTAR:
                {
                alt4=6;
                }
                break;
            case EVAL:
                {
                alt4=7;
                }
                break;
            case ALL:
                {
                alt4=8;
                }
                break;
            case CHOOSE:
                {
                alt4=9;
                }
                break;
            case LOOKUP:
                {
                alt4=10;
                }
                break;
            case PREFIX:
            case PRETTYPRINT:
            case LIST:
            case DEFINE:
            case SIZE:
                {
                alt4=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ExploreGrammar.g:52:4: forward_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_command_in_command327);
                    forward_command7=forward_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_command7.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:53:4: reverse_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_command_in_command332);
                    reverse_command8=reverse_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_command8.getTree());

                    }
                    break;
                case 3 :
                    // ExploreGrammar.g:54:4: forward_props_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_props_command_in_command337);
                    forward_props_command9=forward_props_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_props_command9.getTree());

                    }
                    break;
                case 4 :
                    // ExploreGrammar.g:55:4: reverse_props_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_props_command_in_command342);
                    reverse_props_command10=reverse_props_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_props_command10.getTree());

                    }
                    break;
                case 5 :
                    // ExploreGrammar.g:56:4: forward_star_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_star_command_in_command347);
                    forward_star_command11=forward_star_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_star_command11.getTree());

                    }
                    break;
                case 6 :
                    // ExploreGrammar.g:57:4: reverse_star_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_star_command_in_command352);
                    reverse_star_command12=reverse_star_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_star_command12.getTree());

                    }
                    break;
                case 7 :
                    // ExploreGrammar.g:58:4: eval_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_eval_command_in_command357);
                    eval_command13=eval_command();

                    state._fsp--;

                    adaptor.addChild(root_0, eval_command13.getTree());

                    }
                    break;
                case 8 :
                    // ExploreGrammar.g:59:4: all_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_all_command_in_command362);
                    all_command14=all_command();

                    state._fsp--;

                    adaptor.addChild(root_0, all_command14.getTree());

                    }
                    break;
                case 9 :
                    // ExploreGrammar.g:60:4: choose_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_choose_command_in_command367);
                    choose_command15=choose_command();

                    state._fsp--;

                    adaptor.addChild(root_0, choose_command15.getTree());

                    }
                    break;
                case 10 :
                    // ExploreGrammar.g:61:4: lookup_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_command_in_command372);
                    lookup_command16=lookup_command();

                    state._fsp--;

                    adaptor.addChild(root_0, lookup_command16.getTree());

                    }
                    break;
                case 11 :
                    // ExploreGrammar.g:62:4: interpreter_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interpreter_command_in_command378);
                    interpreter_command17=interpreter_command();

                    state._fsp--;

                    adaptor.addChild(root_0, interpreter_command17.getTree());

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
    // ExploreGrammar.g:65:1: interpreter_command : ( list_command | pretty_print_command | define_command | size_command | prefix_command );
    public final ExploreGrammarParser.interpreter_command_return interpreter_command() throws RecognitionException {
        ExploreGrammarParser.interpreter_command_return retval = new ExploreGrammarParser.interpreter_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.list_command_return list_command18 = null;

        ExploreGrammarParser.pretty_print_command_return pretty_print_command19 = null;

        ExploreGrammarParser.define_command_return define_command20 = null;

        ExploreGrammarParser.size_command_return size_command21 = null;

        ExploreGrammarParser.prefix_command_return prefix_command22 = null;



        try {
            // ExploreGrammar.g:66:2: ( list_command | pretty_print_command | define_command | size_command | prefix_command )
            int alt5=5;
            switch ( input.LA(1) ) {
            case LIST:
                {
                alt5=1;
                }
                break;
            case PRETTYPRINT:
                {
                alt5=2;
                }
                break;
            case DEFINE:
                {
                alt5=3;
                }
                break;
            case SIZE:
                {
                alt5=4;
                }
                break;
            case PREFIX:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ExploreGrammar.g:66:4: list_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_list_command_in_interpreter_command393);
                    list_command18=list_command();

                    state._fsp--;

                    adaptor.addChild(root_0, list_command18.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:67:4: pretty_print_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pretty_print_command_in_interpreter_command399);
                    pretty_print_command19=pretty_print_command();

                    state._fsp--;

                    adaptor.addChild(root_0, pretty_print_command19.getTree());

                    }
                    break;
                case 3 :
                    // ExploreGrammar.g:68:4: define_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_define_command_in_interpreter_command404);
                    define_command20=define_command();

                    state._fsp--;

                    adaptor.addChild(root_0, define_command20.getTree());

                    }
                    break;
                case 4 :
                    // ExploreGrammar.g:69:4: size_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_size_command_in_interpreter_command409);
                    size_command21=size_command();

                    state._fsp--;

                    adaptor.addChild(root_0, size_command21.getTree());

                    }
                    break;
                case 5 :
                    // ExploreGrammar.g:70:4: prefix_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_prefix_command_in_interpreter_command414);
                    prefix_command22=prefix_command();

                    state._fsp--;

                    adaptor.addChild(root_0, prefix_command22.getTree());

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
    // ExploreGrammar.g:73:1: forward_command : FORWARD WS name -> ^( FORWARD name ) ;
    public final ExploreGrammarParser.forward_command_return forward_command() throws RecognitionException {
        ExploreGrammarParser.forward_command_return retval = new ExploreGrammarParser.forward_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARD23=null;
        Token WS24=null;
        ExploreGrammarParser.name_return name25 = null;


        Object FORWARD23_tree=null;
        Object WS24_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_FORWARD=new RewriteRuleTokenStream(adaptor,"token FORWARD");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:73:17: ( FORWARD WS name -> ^( FORWARD name ) )
            // ExploreGrammar.g:73:19: FORWARD WS name
            {
            FORWARD23=(Token)match(input,FORWARD,FOLLOW_FORWARD_in_forward_command427);  
            stream_FORWARD.add(FORWARD23);

            WS24=(Token)match(input,WS,FOLLOW_WS_in_forward_command429);  
            stream_WS.add(WS24);

            pushFollow(FOLLOW_name_in_forward_command431);
            name25=name();

            state._fsp--;

            stream_name.add(name25.getTree());


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
            // 73:35: -> ^( FORWARD name )
            {
                // ExploreGrammar.g:73:38: ^( FORWARD name )
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
    // ExploreGrammar.g:74:1: reverse_command : REVERSE WS name -> ^( REVERSE name ) ;
    public final ExploreGrammarParser.reverse_command_return reverse_command() throws RecognitionException {
        ExploreGrammarParser.reverse_command_return retval = new ExploreGrammarParser.reverse_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSE26=null;
        Token WS27=null;
        ExploreGrammarParser.name_return name28 = null;


        Object REVERSE26_tree=null;
        Object WS27_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_REVERSE=new RewriteRuleTokenStream(adaptor,"token REVERSE");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:74:17: ( REVERSE WS name -> ^( REVERSE name ) )
            // ExploreGrammar.g:74:19: REVERSE WS name
            {
            REVERSE26=(Token)match(input,REVERSE,FOLLOW_REVERSE_in_reverse_command447);  
            stream_REVERSE.add(REVERSE26);

            WS27=(Token)match(input,WS,FOLLOW_WS_in_reverse_command449);  
            stream_WS.add(WS27);

            pushFollow(FOLLOW_name_in_reverse_command451);
            name28=name();

            state._fsp--;

            stream_name.add(name28.getTree());


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
            // 74:35: -> ^( REVERSE name )
            {
                // ExploreGrammar.g:74:38: ^( REVERSE name )
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
    // ExploreGrammar.g:75:1: forward_star_command : FORWARDSTAR WS name -> ^( FORWARDSTAR name ) ;
    public final ExploreGrammarParser.forward_star_command_return forward_star_command() throws RecognitionException {
        ExploreGrammarParser.forward_star_command_return retval = new ExploreGrammarParser.forward_star_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARDSTAR29=null;
        Token WS30=null;
        ExploreGrammarParser.name_return name31 = null;


        Object FORWARDSTAR29_tree=null;
        Object WS30_tree=null;
        RewriteRuleTokenStream stream_FORWARDSTAR=new RewriteRuleTokenStream(adaptor,"token FORWARDSTAR");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:75:22: ( FORWARDSTAR WS name -> ^( FORWARDSTAR name ) )
            // ExploreGrammar.g:75:24: FORWARDSTAR WS name
            {
            FORWARDSTAR29=(Token)match(input,FORWARDSTAR,FOLLOW_FORWARDSTAR_in_forward_star_command468);  
            stream_FORWARDSTAR.add(FORWARDSTAR29);

            WS30=(Token)match(input,WS,FOLLOW_WS_in_forward_star_command470);  
            stream_WS.add(WS30);

            pushFollow(FOLLOW_name_in_forward_star_command472);
            name31=name();

            state._fsp--;

            stream_name.add(name31.getTree());


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
            // 75:44: -> ^( FORWARDSTAR name )
            {
                // ExploreGrammar.g:75:47: ^( FORWARDSTAR name )
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
    // ExploreGrammar.g:76:1: reverse_star_command : REVERSESTAR WS name -> ^( REVERSESTAR name ) ;
    public final ExploreGrammarParser.reverse_star_command_return reverse_star_command() throws RecognitionException {
        ExploreGrammarParser.reverse_star_command_return retval = new ExploreGrammarParser.reverse_star_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSESTAR32=null;
        Token WS33=null;
        ExploreGrammarParser.name_return name34 = null;


        Object REVERSESTAR32_tree=null;
        Object WS33_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_REVERSESTAR=new RewriteRuleTokenStream(adaptor,"token REVERSESTAR");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:76:22: ( REVERSESTAR WS name -> ^( REVERSESTAR name ) )
            // ExploreGrammar.g:76:24: REVERSESTAR WS name
            {
            REVERSESTAR32=(Token)match(input,REVERSESTAR,FOLLOW_REVERSESTAR_in_reverse_star_command488);  
            stream_REVERSESTAR.add(REVERSESTAR32);

            WS33=(Token)match(input,WS,FOLLOW_WS_in_reverse_star_command490);  
            stream_WS.add(WS33);

            pushFollow(FOLLOW_name_in_reverse_star_command492);
            name34=name();

            state._fsp--;

            stream_name.add(name34.getTree());


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
            // 76:44: -> ^( REVERSESTAR name )
            {
                // ExploreGrammar.g:76:47: ^( REVERSESTAR name )
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
    // ExploreGrammar.g:77:1: forward_props_command : FORWARDPROPS -> ^( FORWARDPROPS ) ;
    public final ExploreGrammarParser.forward_props_command_return forward_props_command() throws RecognitionException {
        ExploreGrammarParser.forward_props_command_return retval = new ExploreGrammarParser.forward_props_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARDPROPS35=null;

        Object FORWARDPROPS35_tree=null;
        RewriteRuleTokenStream stream_FORWARDPROPS=new RewriteRuleTokenStream(adaptor,"token FORWARDPROPS");

        try {
            // ExploreGrammar.g:77:23: ( FORWARDPROPS -> ^( FORWARDPROPS ) )
            // ExploreGrammar.g:77:25: FORWARDPROPS
            {
            FORWARDPROPS35=(Token)match(input,FORWARDPROPS,FOLLOW_FORWARDPROPS_in_forward_props_command509);  
            stream_FORWARDPROPS.add(FORWARDPROPS35);



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
            // 77:38: -> ^( FORWARDPROPS )
            {
                // ExploreGrammar.g:77:41: ^( FORWARDPROPS )
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
    // ExploreGrammar.g:78:1: reverse_props_command : REVERSEPROPS -> ^( REVERSEPROPS ) ;
    public final ExploreGrammarParser.reverse_props_command_return reverse_props_command() throws RecognitionException {
        ExploreGrammarParser.reverse_props_command_return retval = new ExploreGrammarParser.reverse_props_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSEPROPS36=null;

        Object REVERSEPROPS36_tree=null;
        RewriteRuleTokenStream stream_REVERSEPROPS=new RewriteRuleTokenStream(adaptor,"token REVERSEPROPS");

        try {
            // ExploreGrammar.g:78:23: ( REVERSEPROPS -> ^( REVERSEPROPS ) )
            // ExploreGrammar.g:78:25: REVERSEPROPS
            {
            REVERSEPROPS36=(Token)match(input,REVERSEPROPS,FOLLOW_REVERSEPROPS_in_reverse_props_command524);  
            stream_REVERSEPROPS.add(REVERSEPROPS36);



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
            // 78:38: -> ^( REVERSEPROPS )
            {
                // ExploreGrammar.g:78:41: ^( REVERSEPROPS )
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
    // ExploreGrammar.g:80:1: lookup_command : LOOKUP WS typed_literal -> ^( LOOKUP typed_literal ) ;
    public final ExploreGrammarParser.lookup_command_return lookup_command() throws RecognitionException {
        ExploreGrammarParser.lookup_command_return retval = new ExploreGrammarParser.lookup_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LOOKUP37=null;
        Token WS38=null;
        ExploreGrammarParser.typed_literal_return typed_literal39 = null;


        Object LOOKUP37_tree=null;
        Object WS38_tree=null;
        RewriteRuleTokenStream stream_LOOKUP=new RewriteRuleTokenStream(adaptor,"token LOOKUP");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleSubtreeStream stream_typed_literal=new RewriteRuleSubtreeStream(adaptor,"rule typed_literal");
        try {
            // ExploreGrammar.g:80:16: ( LOOKUP WS typed_literal -> ^( LOOKUP typed_literal ) )
            // ExploreGrammar.g:80:18: LOOKUP WS typed_literal
            {
            LOOKUP37=(Token)match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup_command540);  
            stream_LOOKUP.add(LOOKUP37);

            WS38=(Token)match(input,WS,FOLLOW_WS_in_lookup_command542);  
            stream_WS.add(WS38);

            pushFollow(FOLLOW_typed_literal_in_lookup_command544);
            typed_literal39=typed_literal();

            state._fsp--;

            stream_typed_literal.add(typed_literal39.getTree());


            // AST REWRITE
            // elements: LOOKUP, typed_literal
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 80:42: -> ^( LOOKUP typed_literal )
            {
                // ExploreGrammar.g:80:45: ^( LOOKUP typed_literal )
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
    // ExploreGrammar.g:81:1: eval_command : EVAL WS name -> ^( EVAL name ) ;
    public final ExploreGrammarParser.eval_command_return eval_command() throws RecognitionException {
        ExploreGrammarParser.eval_command_return retval = new ExploreGrammarParser.eval_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EVAL40=null;
        Token WS41=null;
        ExploreGrammarParser.name_return name42 = null;


        Object EVAL40_tree=null;
        Object WS41_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_EVAL=new RewriteRuleTokenStream(adaptor,"token EVAL");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:81:14: ( EVAL WS name -> ^( EVAL name ) )
            // ExploreGrammar.g:81:16: EVAL WS name
            {
            EVAL40=(Token)match(input,EVAL,FOLLOW_EVAL_in_eval_command561);  
            stream_EVAL.add(EVAL40);

            WS41=(Token)match(input,WS,FOLLOW_WS_in_eval_command563);  
            stream_WS.add(WS41);

            pushFollow(FOLLOW_name_in_eval_command565);
            name42=name();

            state._fsp--;

            stream_name.add(name42.getTree());


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
            // 81:29: -> ^( EVAL name )
            {
                // ExploreGrammar.g:81:32: ^( EVAL name )
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
    // ExploreGrammar.g:82:1: all_command : ALL -> ^( ALL ) ;
    public final ExploreGrammarParser.all_command_return all_command() throws RecognitionException {
        ExploreGrammarParser.all_command_return retval = new ExploreGrammarParser.all_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL43=null;

        Object ALL43_tree=null;
        RewriteRuleTokenStream stream_ALL=new RewriteRuleTokenStream(adaptor,"token ALL");

        try {
            // ExploreGrammar.g:82:13: ( ALL -> ^( ALL ) )
            // ExploreGrammar.g:82:15: ALL
            {
            ALL43=(Token)match(input,ALL,FOLLOW_ALL_in_all_command581);  
            stream_ALL.add(ALL43);



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
            // 82:19: -> ^( ALL )
            {
                // ExploreGrammar.g:82:22: ^( ALL )
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
    // ExploreGrammar.g:83:1: choose_command : CHOOSE -> ^( CHOOSE ) ;
    public final ExploreGrammarParser.choose_command_return choose_command() throws RecognitionException {
        ExploreGrammarParser.choose_command_return retval = new ExploreGrammarParser.choose_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHOOSE44=null;

        Object CHOOSE44_tree=null;
        RewriteRuleTokenStream stream_CHOOSE=new RewriteRuleTokenStream(adaptor,"token CHOOSE");

        try {
            // ExploreGrammar.g:83:16: ( CHOOSE -> ^( CHOOSE ) )
            // ExploreGrammar.g:83:18: CHOOSE
            {
            CHOOSE44=(Token)match(input,CHOOSE,FOLLOW_CHOOSE_in_choose_command595);  
            stream_CHOOSE.add(CHOOSE44);



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
            // 83:25: -> ^( CHOOSE )
            {
                // ExploreGrammar.g:83:28: ^( CHOOSE )
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
    // ExploreGrammar.g:85:1: define_command : DEFINE WS VARIABLE -> ^( DEFINE VARIABLE ) ;
    public final ExploreGrammarParser.define_command_return define_command() throws RecognitionException {
        ExploreGrammarParser.define_command_return retval = new ExploreGrammarParser.define_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DEFINE45=null;
        Token WS46=null;
        Token VARIABLE47=null;

        Object DEFINE45_tree=null;
        Object WS46_tree=null;
        Object VARIABLE47_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
        RewriteRuleTokenStream stream_DEFINE=new RewriteRuleTokenStream(adaptor,"token DEFINE");

        try {
            // ExploreGrammar.g:85:16: ( DEFINE WS VARIABLE -> ^( DEFINE VARIABLE ) )
            // ExploreGrammar.g:85:19: DEFINE WS VARIABLE
            {
            DEFINE45=(Token)match(input,DEFINE,FOLLOW_DEFINE_in_define_command613);  
            stream_DEFINE.add(DEFINE45);

            WS46=(Token)match(input,WS,FOLLOW_WS_in_define_command615);  
            stream_WS.add(WS46);

            VARIABLE47=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_define_command617);  
            stream_VARIABLE.add(VARIABLE47);



            // AST REWRITE
            // elements: DEFINE, VARIABLE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 85:38: -> ^( DEFINE VARIABLE )
            {
                // ExploreGrammar.g:85:41: ^( DEFINE VARIABLE )
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
    // ExploreGrammar.g:86:1: list_command : LIST -> ^( LIST ) ;
    public final ExploreGrammarParser.list_command_return list_command() throws RecognitionException {
        ExploreGrammarParser.list_command_return retval = new ExploreGrammarParser.list_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LIST48=null;

        Object LIST48_tree=null;
        RewriteRuleTokenStream stream_LIST=new RewriteRuleTokenStream(adaptor,"token LIST");

        try {
            // ExploreGrammar.g:86:14: ( LIST -> ^( LIST ) )
            // ExploreGrammar.g:86:17: LIST
            {
            LIST48=(Token)match(input,LIST,FOLLOW_LIST_in_list_command635);  
            stream_LIST.add(LIST48);



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
            // 86:23: -> ^( LIST )
            {
                // ExploreGrammar.g:86:26: ^( LIST )
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
    // ExploreGrammar.g:87:1: pretty_print_command : PRETTYPRINT -> ^( PRETTYPRINT ) ;
    public final ExploreGrammarParser.pretty_print_command_return pretty_print_command() throws RecognitionException {
        ExploreGrammarParser.pretty_print_command_return retval = new ExploreGrammarParser.pretty_print_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PRETTYPRINT49=null;

        Object PRETTYPRINT49_tree=null;
        RewriteRuleTokenStream stream_PRETTYPRINT=new RewriteRuleTokenStream(adaptor,"token PRETTYPRINT");

        try {
            // ExploreGrammar.g:87:22: ( PRETTYPRINT -> ^( PRETTYPRINT ) )
            // ExploreGrammar.g:87:25: PRETTYPRINT
            {
            PRETTYPRINT49=(Token)match(input,PRETTYPRINT,FOLLOW_PRETTYPRINT_in_pretty_print_command651);  
            stream_PRETTYPRINT.add(PRETTYPRINT49);



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
            // 87:37: -> ^( PRETTYPRINT )
            {
                // ExploreGrammar.g:87:40: ^( PRETTYPRINT )
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
    // ExploreGrammar.g:88:1: size_command : SIZE -> ^( SIZE ) ;
    public final ExploreGrammarParser.size_command_return size_command() throws RecognitionException {
        ExploreGrammarParser.size_command_return retval = new ExploreGrammarParser.size_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SIZE50=null;

        Object SIZE50_tree=null;
        RewriteRuleTokenStream stream_SIZE=new RewriteRuleTokenStream(adaptor,"token SIZE");

        try {
            // ExploreGrammar.g:88:14: ( SIZE -> ^( SIZE ) )
            // ExploreGrammar.g:88:17: SIZE
            {
            SIZE50=(Token)match(input,SIZE,FOLLOW_SIZE_in_size_command666);  
            stream_SIZE.add(SIZE50);



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
            // 88:22: -> ^( SIZE )
            {
                // ExploreGrammar.g:88:25: ^( SIZE )
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
    // ExploreGrammar.g:89:1: prefix_command : PREFIX WS KEYWORD WS IRI -> ^( PREFIX KEYWORD IRI ) ;
    public final ExploreGrammarParser.prefix_command_return prefix_command() throws RecognitionException {
        ExploreGrammarParser.prefix_command_return retval = new ExploreGrammarParser.prefix_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PREFIX51=null;
        Token WS52=null;
        Token KEYWORD53=null;
        Token WS54=null;
        Token IRI55=null;

        Object PREFIX51_tree=null;
        Object WS52_tree=null;
        Object KEYWORD53_tree=null;
        Object WS54_tree=null;
        Object IRI55_tree=null;
        RewriteRuleTokenStream stream_PREFIX=new RewriteRuleTokenStream(adaptor,"token PREFIX");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_KEYWORD=new RewriteRuleTokenStream(adaptor,"token KEYWORD");
        RewriteRuleTokenStream stream_IRI=new RewriteRuleTokenStream(adaptor,"token IRI");

        try {
            // ExploreGrammar.g:89:16: ( PREFIX WS KEYWORD WS IRI -> ^( PREFIX KEYWORD IRI ) )
            // ExploreGrammar.g:89:18: PREFIX WS KEYWORD WS IRI
            {
            PREFIX51=(Token)match(input,PREFIX,FOLLOW_PREFIX_in_prefix_command680);  
            stream_PREFIX.add(PREFIX51);

            WS52=(Token)match(input,WS,FOLLOW_WS_in_prefix_command682);  
            stream_WS.add(WS52);

            KEYWORD53=(Token)match(input,KEYWORD,FOLLOW_KEYWORD_in_prefix_command684);  
            stream_KEYWORD.add(KEYWORD53);

            WS54=(Token)match(input,WS,FOLLOW_WS_in_prefix_command686);  
            stream_WS.add(WS54);

            IRI55=(Token)match(input,IRI,FOLLOW_IRI_in_prefix_command688);  
            stream_IRI.add(IRI55);



            // AST REWRITE
            // elements: IRI, KEYWORD, PREFIX
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 89:43: -> ^( PREFIX KEYWORD IRI )
            {
                // ExploreGrammar.g:89:46: ^( PREFIX KEYWORD IRI )
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

    public static class custom_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "custom_command"
    // ExploreGrammar.g:91:1: custom_command : KEYWORD ( WS value )* -> ^( KEYWORD ( value )* ) ;
    public final ExploreGrammarParser.custom_command_return custom_command() throws RecognitionException {
        ExploreGrammarParser.custom_command_return retval = new ExploreGrammarParser.custom_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KEYWORD56=null;
        Token WS57=null;
        ExploreGrammarParser.value_return value58 = null;


        Object KEYWORD56_tree=null;
        Object WS57_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_KEYWORD=new RewriteRuleTokenStream(adaptor,"token KEYWORD");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // ExploreGrammar.g:91:16: ( KEYWORD ( WS value )* -> ^( KEYWORD ( value )* ) )
            // ExploreGrammar.g:91:18: KEYWORD ( WS value )*
            {
            KEYWORD56=(Token)match(input,KEYWORD,FOLLOW_KEYWORD_in_custom_command708);  
            stream_KEYWORD.add(KEYWORD56);

            // ExploreGrammar.g:91:26: ( WS value )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==WS) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ExploreGrammar.g:91:27: WS value
            	    {
            	    WS57=(Token)match(input,WS,FOLLOW_WS_in_custom_command711);  
            	    stream_WS.add(WS57);

            	    pushFollow(FOLLOW_value_in_custom_command713);
            	    value58=value();

            	    state._fsp--;

            	    stream_value.add(value58.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
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
            // 91:38: -> ^( KEYWORD ( value )* )
            {
                // ExploreGrammar.g:91:41: ^( KEYWORD ( value )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_KEYWORD.nextNode(), root_1);

                // ExploreGrammar.g:91:52: ( value )*
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
    // ExploreGrammar.g:93:1: eol : ( ( WS )? NEWLINE )+ ;
    public final ExploreGrammarParser.eol_return eol() throws RecognitionException {
        ExploreGrammarParser.eol_return retval = new ExploreGrammarParser.eol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS59=null;
        Token NEWLINE60=null;

        Object WS59_tree=null;
        Object NEWLINE60_tree=null;

        try {
            // ExploreGrammar.g:93:5: ( ( ( WS )? NEWLINE )+ )
            // ExploreGrammar.g:93:7: ( ( WS )? NEWLINE )+
            {
            root_0 = (Object)adaptor.nil();

            // ExploreGrammar.g:93:7: ( ( WS )? NEWLINE )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==WS||LA8_0==NEWLINE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ExploreGrammar.g:93:8: ( WS )? NEWLINE
            	    {
            	    // ExploreGrammar.g:93:8: ( WS )?
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==WS) ) {
            	        alt7=1;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // ExploreGrammar.g:93:8: WS
            	            {
            	            WS59=(Token)match(input,WS,FOLLOW_WS_in_eol736); 
            	            WS59_tree = (Object)adaptor.create(WS59);
            	            adaptor.addChild(root_0, WS59_tree);


            	            }
            	            break;

            	    }

            	    NEWLINE60=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eol739); 
            	    NEWLINE60_tree = (Object)adaptor.create(NEWLINE60);
            	    adaptor.addChild(root_0, NEWLINE60_tree);


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
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
    // ExploreGrammar.g:95:1: value : ( name | typed_literal );
    public final ExploreGrammarParser.value_return value() throws RecognitionException {
        ExploreGrammarParser.value_return retval = new ExploreGrammarParser.value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.name_return name61 = null;

        ExploreGrammarParser.typed_literal_return typed_literal62 = null;



        try {
            // ExploreGrammar.g:96:2: ( name | typed_literal )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==VARIABLE||LA9_0==IRI||LA9_0==PREFIXED) ) {
                alt9=1;
            }
            else if ( (LA9_0==LITERAL) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ExploreGrammar.g:96:4: name
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_name_in_value752);
                    name61=name();

                    state._fsp--;

                    adaptor.addChild(root_0, name61.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:97:4: typed_literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_typed_literal_in_value757);
                    typed_literal62=typed_literal();

                    state._fsp--;

                    adaptor.addChild(root_0, typed_literal62.getTree());

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
    // ExploreGrammar.g:100:1: name : ( resource | VARIABLE );
    public final ExploreGrammarParser.name_return name() throws RecognitionException {
        ExploreGrammarParser.name_return retval = new ExploreGrammarParser.name_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VARIABLE64=null;
        ExploreGrammarParser.resource_return resource63 = null;


        Object VARIABLE64_tree=null;

        try {
            // ExploreGrammar.g:101:2: ( resource | VARIABLE )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==IRI||LA10_0==PREFIXED) ) {
                alt10=1;
            }
            else if ( (LA10_0==VARIABLE) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ExploreGrammar.g:101:4: resource
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_resource_in_name772);
                    resource63=resource();

                    state._fsp--;

                    adaptor.addChild(root_0, resource63.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:102:4: VARIABLE
                    {
                    root_0 = (Object)adaptor.nil();

                    VARIABLE64=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_name777); 
                    VARIABLE64_tree = (Object)adaptor.create(VARIABLE64);
                    adaptor.addChild(root_0, VARIABLE64_tree);


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
    // ExploreGrammar.g:105:1: resource : ( PREFIXED | IRI );
    public final ExploreGrammarParser.resource_return resource() throws RecognitionException {
        ExploreGrammarParser.resource_return retval = new ExploreGrammarParser.resource_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set65=null;

        Object set65_tree=null;

        try {
            // ExploreGrammar.g:106:2: ( PREFIXED | IRI )
            // ExploreGrammar.g:
            {
            root_0 = (Object)adaptor.nil();

            set65=(Token)input.LT(1);
            if ( input.LA(1)==IRI||input.LA(1)==PREFIXED ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set65));
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
    // ExploreGrammar.g:110:1: typed_literal : ( LITERAL -> ^( TYPEDLITERAL LITERAL BLANKTYPE ) | LITERAL TYPED resource -> ^( TYPEDLITERAL LITERAL resource ) );
    public final ExploreGrammarParser.typed_literal_return typed_literal() throws RecognitionException {
        ExploreGrammarParser.typed_literal_return retval = new ExploreGrammarParser.typed_literal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LITERAL66=null;
        Token LITERAL67=null;
        Token TYPED68=null;
        ExploreGrammarParser.resource_return resource69 = null;


        Object LITERAL66_tree=null;
        Object LITERAL67_tree=null;
        Object TYPED68_tree=null;
        RewriteRuleTokenStream stream_TYPED=new RewriteRuleTokenStream(adaptor,"token TYPED");
        RewriteRuleTokenStream stream_LITERAL=new RewriteRuleTokenStream(adaptor,"token LITERAL");
        RewriteRuleSubtreeStream stream_resource=new RewriteRuleSubtreeStream(adaptor,"rule resource");
        try {
            // ExploreGrammar.g:111:2: ( LITERAL -> ^( TYPEDLITERAL LITERAL BLANKTYPE ) | LITERAL TYPED resource -> ^( TYPEDLITERAL LITERAL resource ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LITERAL) ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1==TYPED) ) {
                    alt11=2;
                }
                else if ( (LA11_1==EOF||LA11_1==WS) ) {
                    alt11=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ExploreGrammar.g:111:4: LITERAL
                    {
                    LITERAL66=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_typed_literal811);  
                    stream_LITERAL.add(LITERAL66);



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
                    // 111:13: -> ^( TYPEDLITERAL LITERAL BLANKTYPE )
                    {
                        // ExploreGrammar.g:111:16: ^( TYPEDLITERAL LITERAL BLANKTYPE )
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
                    // ExploreGrammar.g:112:4: LITERAL TYPED resource
                    {
                    LITERAL67=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_typed_literal828);  
                    stream_LITERAL.add(LITERAL67);

                    TYPED68=(Token)match(input,TYPED,FOLLOW_TYPED_in_typed_literal830);  
                    stream_TYPED.add(TYPED68);

                    pushFollow(FOLLOW_resource_in_typed_literal832);
                    resource69=resource();

                    state._fsp--;

                    stream_resource.add(resource69.getTree());


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
                    // 112:27: -> ^( TYPEDLITERAL LITERAL resource )
                    {
                        // ExploreGrammar.g:112:30: ^( TYPEDLITERAL LITERAL resource )
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


 

    public static final BitSet FOLLOW_WS_in_program297 = new BitSet(new long[]{0x000000000203FFF0L});
    public static final BitSet FOLLOW_command_in_program300 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_program303 = new BitSet(new long[]{0x000000000203FFF0L});
    public static final BitSet FOLLOW_command_in_program305 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_program309 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_command_in_command327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_command_in_command332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_props_command_in_command337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_props_command_in_command342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_star_command_in_command347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_star_command_in_command352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eval_command_in_command357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_all_command_in_command362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_choose_command_in_command367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_command_in_command372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interpreter_command_in_command378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_command_in_interpreter_command393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pretty_print_command_in_interpreter_command399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_command_in_interpreter_command404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_size_command_in_interpreter_command409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefix_command_in_interpreter_command414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARD_in_forward_command427 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_forward_command429 = new BitSet(new long[]{0x00000002A0000000L});
    public static final BitSet FOLLOW_name_in_forward_command431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSE_in_reverse_command447 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_reverse_command449 = new BitSet(new long[]{0x00000002A0000000L});
    public static final BitSet FOLLOW_name_in_reverse_command451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARDSTAR_in_forward_star_command468 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_forward_star_command470 = new BitSet(new long[]{0x00000002A0000000L});
    public static final BitSet FOLLOW_name_in_forward_star_command472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSESTAR_in_reverse_star_command488 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_reverse_star_command490 = new BitSet(new long[]{0x00000002A0000000L});
    public static final BitSet FOLLOW_name_in_reverse_star_command492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARDPROPS_in_forward_props_command509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSEPROPS_in_reverse_props_command524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup_command540 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_lookup_command542 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_typed_literal_in_lookup_command544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EVAL_in_eval_command561 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_eval_command563 = new BitSet(new long[]{0x00000002A0000000L});
    public static final BitSet FOLLOW_name_in_eval_command565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_all_command581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHOOSE_in_choose_command595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFINE_in_define_command613 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_define_command615 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_VARIABLE_in_define_command617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list_command635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRETTYPRINT_in_pretty_print_command651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_size_command666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PREFIX_in_prefix_command680 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_prefix_command682 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_KEYWORD_in_prefix_command684 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_WS_in_prefix_command686 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_IRI_in_prefix_command688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_in_custom_command708 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_WS_in_custom_command711 = new BitSet(new long[]{0x00000006A0000000L});
    public static final BitSet FOLLOW_value_in_custom_command713 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_WS_in_eol736 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_eol739 = new BitSet(new long[]{0x0000000110000002L});
    public static final BitSet FOLLOW_name_in_value752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typed_literal_in_value757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resource_in_name772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_name777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_resource0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_in_typed_literal811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_in_typed_literal828 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_TYPED_in_typed_literal830 = new BitSet(new long[]{0x0000000280000000L});
    public static final BitSet FOLLOW_resource_in_typed_literal832 = new BitSet(new long[]{0x0000000000000002L});

}