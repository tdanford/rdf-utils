// $ANTLR 3.3 Nov 30, 2010 12:50:56 ExploreGrammar.g 2010-12-13 14:16:08
 
package tdanford.browser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class ExploreGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "FORWARDPROPS", "REVERSEPROPS", "FORWARDSTAR", "REVERSESTAR", "FORWARD", "REVERSE", "EVAL", "LOOKUP", "ALL", "CHOOSE", "PRETTYPRINT", "LIST", "DEFINE", "DOLLAR", "QUOTE", "LANGLE", "RANGLE", "COLON", "TYPED", "SLASH", "SIZE", "BLANKTYPE", "TYPEDLITERAL", "WS", "VARIABLE", "KEYWORD", "NEWLINE", "PREFIXED", "IRI", "LITERAL", "QUOTED", "LETTER", "DIGIT", "INTEGER"
    };
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
    // ExploreGrammar.g:48:1: program : ( WS )? command ( WS command )* ( WS )? EOF -> ( command )+ ;
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
            // ExploreGrammar.g:48:9: ( ( WS )? command ( WS command )* ( WS )? EOF -> ( command )+ )
            // ExploreGrammar.g:48:11: ( WS )? command ( WS command )* ( WS )? EOF
            {
            // ExploreGrammar.g:48:11: ( WS )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==WS) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ExploreGrammar.g:48:11: WS
                    {
                    WS1=(Token)match(input,WS,FOLLOW_WS_in_program287);  
                    stream_WS.add(WS1);


                    }
                    break;

            }

            pushFollow(FOLLOW_command_in_program290);
            command2=command();

            state._fsp--;

            stream_command.add(command2.getTree());
            // ExploreGrammar.g:48:23: ( WS command )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==WS) ) {
                    int LA2_1 = input.LA(2);

                    if ( ((LA2_1>=FORWARDPROPS && LA2_1<=DEFINE)||LA2_1==SIZE) ) {
                        alt2=1;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // ExploreGrammar.g:48:24: WS command
            	    {
            	    WS3=(Token)match(input,WS,FOLLOW_WS_in_program293);  
            	    stream_WS.add(WS3);

            	    pushFollow(FOLLOW_command_in_program295);
            	    command4=command();

            	    state._fsp--;

            	    stream_command.add(command4.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ExploreGrammar.g:48:37: ( WS )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==WS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ExploreGrammar.g:48:37: WS
                    {
                    WS5=(Token)match(input,WS,FOLLOW_WS_in_program299);  
                    stream_WS.add(WS5);


                    }
                    break;

            }

            EOF6=(Token)match(input,EOF,FOLLOW_EOF_in_program302);  
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
            // 48:45: -> ( command )+
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
    // ExploreGrammar.g:50:1: command : ( forward_command | reverse_command | forward_props_command | reverse_props_command | forward_star_command | reverse_star_command | eval_command | all_command | choose_command | lookup_command | interpreter_command );
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
            // ExploreGrammar.g:51:2: ( forward_command | reverse_command | forward_props_command | reverse_props_command | forward_star_command | reverse_star_command | eval_command | all_command | choose_command | lookup_command | interpreter_command )
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
                    // ExploreGrammar.g:51:4: forward_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_command_in_command317);
                    forward_command7=forward_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_command7.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:52:4: reverse_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_command_in_command322);
                    reverse_command8=reverse_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_command8.getTree());

                    }
                    break;
                case 3 :
                    // ExploreGrammar.g:53:4: forward_props_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_props_command_in_command327);
                    forward_props_command9=forward_props_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_props_command9.getTree());

                    }
                    break;
                case 4 :
                    // ExploreGrammar.g:54:4: reverse_props_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_props_command_in_command332);
                    reverse_props_command10=reverse_props_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_props_command10.getTree());

                    }
                    break;
                case 5 :
                    // ExploreGrammar.g:55:4: forward_star_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forward_star_command_in_command337);
                    forward_star_command11=forward_star_command();

                    state._fsp--;

                    adaptor.addChild(root_0, forward_star_command11.getTree());

                    }
                    break;
                case 6 :
                    // ExploreGrammar.g:56:4: reverse_star_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_reverse_star_command_in_command342);
                    reverse_star_command12=reverse_star_command();

                    state._fsp--;

                    adaptor.addChild(root_0, reverse_star_command12.getTree());

                    }
                    break;
                case 7 :
                    // ExploreGrammar.g:57:4: eval_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_eval_command_in_command347);
                    eval_command13=eval_command();

                    state._fsp--;

                    adaptor.addChild(root_0, eval_command13.getTree());

                    }
                    break;
                case 8 :
                    // ExploreGrammar.g:58:4: all_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_all_command_in_command352);
                    all_command14=all_command();

                    state._fsp--;

                    adaptor.addChild(root_0, all_command14.getTree());

                    }
                    break;
                case 9 :
                    // ExploreGrammar.g:59:4: choose_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_choose_command_in_command357);
                    choose_command15=choose_command();

                    state._fsp--;

                    adaptor.addChild(root_0, choose_command15.getTree());

                    }
                    break;
                case 10 :
                    // ExploreGrammar.g:60:4: lookup_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_command_in_command362);
                    lookup_command16=lookup_command();

                    state._fsp--;

                    adaptor.addChild(root_0, lookup_command16.getTree());

                    }
                    break;
                case 11 :
                    // ExploreGrammar.g:61:4: interpreter_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interpreter_command_in_command368);
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
    // ExploreGrammar.g:64:1: interpreter_command : ( list_command | pretty_print_command | define_command | size_command );
    public final ExploreGrammarParser.interpreter_command_return interpreter_command() throws RecognitionException {
        ExploreGrammarParser.interpreter_command_return retval = new ExploreGrammarParser.interpreter_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.list_command_return list_command18 = null;

        ExploreGrammarParser.pretty_print_command_return pretty_print_command19 = null;

        ExploreGrammarParser.define_command_return define_command20 = null;

        ExploreGrammarParser.size_command_return size_command21 = null;



        try {
            // ExploreGrammar.g:65:2: ( list_command | pretty_print_command | define_command | size_command )
            int alt5=4;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ExploreGrammar.g:65:4: list_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_list_command_in_interpreter_command383);
                    list_command18=list_command();

                    state._fsp--;

                    adaptor.addChild(root_0, list_command18.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:66:4: pretty_print_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pretty_print_command_in_interpreter_command389);
                    pretty_print_command19=pretty_print_command();

                    state._fsp--;

                    adaptor.addChild(root_0, pretty_print_command19.getTree());

                    }
                    break;
                case 3 :
                    // ExploreGrammar.g:67:4: define_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_define_command_in_interpreter_command394);
                    define_command20=define_command();

                    state._fsp--;

                    adaptor.addChild(root_0, define_command20.getTree());

                    }
                    break;
                case 4 :
                    // ExploreGrammar.g:68:4: size_command
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_size_command_in_interpreter_command399);
                    size_command21=size_command();

                    state._fsp--;

                    adaptor.addChild(root_0, size_command21.getTree());

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
    // ExploreGrammar.g:71:1: forward_command : FORWARD WS name -> ^( FORWARD name ) ;
    public final ExploreGrammarParser.forward_command_return forward_command() throws RecognitionException {
        ExploreGrammarParser.forward_command_return retval = new ExploreGrammarParser.forward_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARD22=null;
        Token WS23=null;
        ExploreGrammarParser.name_return name24 = null;


        Object FORWARD22_tree=null;
        Object WS23_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_FORWARD=new RewriteRuleTokenStream(adaptor,"token FORWARD");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:71:17: ( FORWARD WS name -> ^( FORWARD name ) )
            // ExploreGrammar.g:71:19: FORWARD WS name
            {
            FORWARD22=(Token)match(input,FORWARD,FOLLOW_FORWARD_in_forward_command412);  
            stream_FORWARD.add(FORWARD22);

            WS23=(Token)match(input,WS,FOLLOW_WS_in_forward_command414);  
            stream_WS.add(WS23);

            pushFollow(FOLLOW_name_in_forward_command416);
            name24=name();

            state._fsp--;

            stream_name.add(name24.getTree());


            // AST REWRITE
            // elements: name, FORWARD
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 71:35: -> ^( FORWARD name )
            {
                // ExploreGrammar.g:71:38: ^( FORWARD name )
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
    // ExploreGrammar.g:72:1: reverse_command : REVERSE WS name -> ^( REVERSE name ) ;
    public final ExploreGrammarParser.reverse_command_return reverse_command() throws RecognitionException {
        ExploreGrammarParser.reverse_command_return retval = new ExploreGrammarParser.reverse_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSE25=null;
        Token WS26=null;
        ExploreGrammarParser.name_return name27 = null;


        Object REVERSE25_tree=null;
        Object WS26_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_REVERSE=new RewriteRuleTokenStream(adaptor,"token REVERSE");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:72:17: ( REVERSE WS name -> ^( REVERSE name ) )
            // ExploreGrammar.g:72:19: REVERSE WS name
            {
            REVERSE25=(Token)match(input,REVERSE,FOLLOW_REVERSE_in_reverse_command432);  
            stream_REVERSE.add(REVERSE25);

            WS26=(Token)match(input,WS,FOLLOW_WS_in_reverse_command434);  
            stream_WS.add(WS26);

            pushFollow(FOLLOW_name_in_reverse_command436);
            name27=name();

            state._fsp--;

            stream_name.add(name27.getTree());


            // AST REWRITE
            // elements: name, REVERSE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 72:35: -> ^( REVERSE name )
            {
                // ExploreGrammar.g:72:38: ^( REVERSE name )
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
    // ExploreGrammar.g:73:1: forward_star_command : FORWARDSTAR WS name -> ^( FORWARDSTAR name ) ;
    public final ExploreGrammarParser.forward_star_command_return forward_star_command() throws RecognitionException {
        ExploreGrammarParser.forward_star_command_return retval = new ExploreGrammarParser.forward_star_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARDSTAR28=null;
        Token WS29=null;
        ExploreGrammarParser.name_return name30 = null;


        Object FORWARDSTAR28_tree=null;
        Object WS29_tree=null;
        RewriteRuleTokenStream stream_FORWARDSTAR=new RewriteRuleTokenStream(adaptor,"token FORWARDSTAR");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:73:22: ( FORWARDSTAR WS name -> ^( FORWARDSTAR name ) )
            // ExploreGrammar.g:73:24: FORWARDSTAR WS name
            {
            FORWARDSTAR28=(Token)match(input,FORWARDSTAR,FOLLOW_FORWARDSTAR_in_forward_star_command453);  
            stream_FORWARDSTAR.add(FORWARDSTAR28);

            WS29=(Token)match(input,WS,FOLLOW_WS_in_forward_star_command455);  
            stream_WS.add(WS29);

            pushFollow(FOLLOW_name_in_forward_star_command457);
            name30=name();

            state._fsp--;

            stream_name.add(name30.getTree());


            // AST REWRITE
            // elements: FORWARDSTAR, name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 73:44: -> ^( FORWARDSTAR name )
            {
                // ExploreGrammar.g:73:47: ^( FORWARDSTAR name )
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
    // ExploreGrammar.g:74:1: reverse_star_command : REVERSESTAR WS name -> ^( REVERSESTAR name ) ;
    public final ExploreGrammarParser.reverse_star_command_return reverse_star_command() throws RecognitionException {
        ExploreGrammarParser.reverse_star_command_return retval = new ExploreGrammarParser.reverse_star_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSESTAR31=null;
        Token WS32=null;
        ExploreGrammarParser.name_return name33 = null;


        Object REVERSESTAR31_tree=null;
        Object WS32_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_REVERSESTAR=new RewriteRuleTokenStream(adaptor,"token REVERSESTAR");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:74:22: ( REVERSESTAR WS name -> ^( REVERSESTAR name ) )
            // ExploreGrammar.g:74:24: REVERSESTAR WS name
            {
            REVERSESTAR31=(Token)match(input,REVERSESTAR,FOLLOW_REVERSESTAR_in_reverse_star_command473);  
            stream_REVERSESTAR.add(REVERSESTAR31);

            WS32=(Token)match(input,WS,FOLLOW_WS_in_reverse_star_command475);  
            stream_WS.add(WS32);

            pushFollow(FOLLOW_name_in_reverse_star_command477);
            name33=name();

            state._fsp--;

            stream_name.add(name33.getTree());


            // AST REWRITE
            // elements: name, REVERSESTAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 74:44: -> ^( REVERSESTAR name )
            {
                // ExploreGrammar.g:74:47: ^( REVERSESTAR name )
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
    // ExploreGrammar.g:75:1: forward_props_command : FORWARDPROPS -> ^( FORWARDPROPS ) ;
    public final ExploreGrammarParser.forward_props_command_return forward_props_command() throws RecognitionException {
        ExploreGrammarParser.forward_props_command_return retval = new ExploreGrammarParser.forward_props_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FORWARDPROPS34=null;

        Object FORWARDPROPS34_tree=null;
        RewriteRuleTokenStream stream_FORWARDPROPS=new RewriteRuleTokenStream(adaptor,"token FORWARDPROPS");

        try {
            // ExploreGrammar.g:75:23: ( FORWARDPROPS -> ^( FORWARDPROPS ) )
            // ExploreGrammar.g:75:25: FORWARDPROPS
            {
            FORWARDPROPS34=(Token)match(input,FORWARDPROPS,FOLLOW_FORWARDPROPS_in_forward_props_command494);  
            stream_FORWARDPROPS.add(FORWARDPROPS34);



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
            // 75:38: -> ^( FORWARDPROPS )
            {
                // ExploreGrammar.g:75:41: ^( FORWARDPROPS )
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
    // ExploreGrammar.g:76:1: reverse_props_command : REVERSEPROPS -> ^( REVERSEPROPS ) ;
    public final ExploreGrammarParser.reverse_props_command_return reverse_props_command() throws RecognitionException {
        ExploreGrammarParser.reverse_props_command_return retval = new ExploreGrammarParser.reverse_props_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REVERSEPROPS35=null;

        Object REVERSEPROPS35_tree=null;
        RewriteRuleTokenStream stream_REVERSEPROPS=new RewriteRuleTokenStream(adaptor,"token REVERSEPROPS");

        try {
            // ExploreGrammar.g:76:23: ( REVERSEPROPS -> ^( REVERSEPROPS ) )
            // ExploreGrammar.g:76:25: REVERSEPROPS
            {
            REVERSEPROPS35=(Token)match(input,REVERSEPROPS,FOLLOW_REVERSEPROPS_in_reverse_props_command509);  
            stream_REVERSEPROPS.add(REVERSEPROPS35);



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
            // 76:38: -> ^( REVERSEPROPS )
            {
                // ExploreGrammar.g:76:41: ^( REVERSEPROPS )
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
    // ExploreGrammar.g:78:1: lookup_command : LOOKUP WS typed_literal -> ^( LOOKUP typed_literal ) ;
    public final ExploreGrammarParser.lookup_command_return lookup_command() throws RecognitionException {
        ExploreGrammarParser.lookup_command_return retval = new ExploreGrammarParser.lookup_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LOOKUP36=null;
        Token WS37=null;
        ExploreGrammarParser.typed_literal_return typed_literal38 = null;


        Object LOOKUP36_tree=null;
        Object WS37_tree=null;
        RewriteRuleTokenStream stream_LOOKUP=new RewriteRuleTokenStream(adaptor,"token LOOKUP");
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleSubtreeStream stream_typed_literal=new RewriteRuleSubtreeStream(adaptor,"rule typed_literal");
        try {
            // ExploreGrammar.g:78:16: ( LOOKUP WS typed_literal -> ^( LOOKUP typed_literal ) )
            // ExploreGrammar.g:78:18: LOOKUP WS typed_literal
            {
            LOOKUP36=(Token)match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup_command525);  
            stream_LOOKUP.add(LOOKUP36);

            WS37=(Token)match(input,WS,FOLLOW_WS_in_lookup_command527);  
            stream_WS.add(WS37);

            pushFollow(FOLLOW_typed_literal_in_lookup_command529);
            typed_literal38=typed_literal();

            state._fsp--;

            stream_typed_literal.add(typed_literal38.getTree());


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
            // 78:42: -> ^( LOOKUP typed_literal )
            {
                // ExploreGrammar.g:78:45: ^( LOOKUP typed_literal )
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
    // ExploreGrammar.g:79:1: eval_command : EVAL WS name -> ^( EVAL name ) ;
    public final ExploreGrammarParser.eval_command_return eval_command() throws RecognitionException {
        ExploreGrammarParser.eval_command_return retval = new ExploreGrammarParser.eval_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EVAL39=null;
        Token WS40=null;
        ExploreGrammarParser.name_return name41 = null;


        Object EVAL39_tree=null;
        Object WS40_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_EVAL=new RewriteRuleTokenStream(adaptor,"token EVAL");
        RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
        try {
            // ExploreGrammar.g:79:14: ( EVAL WS name -> ^( EVAL name ) )
            // ExploreGrammar.g:79:16: EVAL WS name
            {
            EVAL39=(Token)match(input,EVAL,FOLLOW_EVAL_in_eval_command546);  
            stream_EVAL.add(EVAL39);

            WS40=(Token)match(input,WS,FOLLOW_WS_in_eval_command548);  
            stream_WS.add(WS40);

            pushFollow(FOLLOW_name_in_eval_command550);
            name41=name();

            state._fsp--;

            stream_name.add(name41.getTree());


            // AST REWRITE
            // elements: EVAL, name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 79:29: -> ^( EVAL name )
            {
                // ExploreGrammar.g:79:32: ^( EVAL name )
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
    // ExploreGrammar.g:80:1: all_command : ALL -> ^( ALL ) ;
    public final ExploreGrammarParser.all_command_return all_command() throws RecognitionException {
        ExploreGrammarParser.all_command_return retval = new ExploreGrammarParser.all_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL42=null;

        Object ALL42_tree=null;
        RewriteRuleTokenStream stream_ALL=new RewriteRuleTokenStream(adaptor,"token ALL");

        try {
            // ExploreGrammar.g:80:13: ( ALL -> ^( ALL ) )
            // ExploreGrammar.g:80:15: ALL
            {
            ALL42=(Token)match(input,ALL,FOLLOW_ALL_in_all_command566);  
            stream_ALL.add(ALL42);



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
            // 80:19: -> ^( ALL )
            {
                // ExploreGrammar.g:80:22: ^( ALL )
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
    // ExploreGrammar.g:81:1: choose_command : CHOOSE -> ^( CHOOSE ) ;
    public final ExploreGrammarParser.choose_command_return choose_command() throws RecognitionException {
        ExploreGrammarParser.choose_command_return retval = new ExploreGrammarParser.choose_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHOOSE43=null;

        Object CHOOSE43_tree=null;
        RewriteRuleTokenStream stream_CHOOSE=new RewriteRuleTokenStream(adaptor,"token CHOOSE");

        try {
            // ExploreGrammar.g:81:16: ( CHOOSE -> ^( CHOOSE ) )
            // ExploreGrammar.g:81:18: CHOOSE
            {
            CHOOSE43=(Token)match(input,CHOOSE,FOLLOW_CHOOSE_in_choose_command580);  
            stream_CHOOSE.add(CHOOSE43);



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
            // 81:25: -> ^( CHOOSE )
            {
                // ExploreGrammar.g:81:28: ^( CHOOSE )
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
    // ExploreGrammar.g:83:1: define_command : DEFINE WS VARIABLE -> ^( DEFINE VARIABLE ) ;
    public final ExploreGrammarParser.define_command_return define_command() throws RecognitionException {
        ExploreGrammarParser.define_command_return retval = new ExploreGrammarParser.define_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DEFINE44=null;
        Token WS45=null;
        Token VARIABLE46=null;

        Object DEFINE44_tree=null;
        Object WS45_tree=null;
        Object VARIABLE46_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
        RewriteRuleTokenStream stream_DEFINE=new RewriteRuleTokenStream(adaptor,"token DEFINE");

        try {
            // ExploreGrammar.g:83:16: ( DEFINE WS VARIABLE -> ^( DEFINE VARIABLE ) )
            // ExploreGrammar.g:83:19: DEFINE WS VARIABLE
            {
            DEFINE44=(Token)match(input,DEFINE,FOLLOW_DEFINE_in_define_command598);  
            stream_DEFINE.add(DEFINE44);

            WS45=(Token)match(input,WS,FOLLOW_WS_in_define_command600);  
            stream_WS.add(WS45);

            VARIABLE46=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_define_command602);  
            stream_VARIABLE.add(VARIABLE46);



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
            // 83:38: -> ^( DEFINE VARIABLE )
            {
                // ExploreGrammar.g:83:41: ^( DEFINE VARIABLE )
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
    // ExploreGrammar.g:84:1: list_command : LIST -> ^( LIST ) ;
    public final ExploreGrammarParser.list_command_return list_command() throws RecognitionException {
        ExploreGrammarParser.list_command_return retval = new ExploreGrammarParser.list_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LIST47=null;

        Object LIST47_tree=null;
        RewriteRuleTokenStream stream_LIST=new RewriteRuleTokenStream(adaptor,"token LIST");

        try {
            // ExploreGrammar.g:84:14: ( LIST -> ^( LIST ) )
            // ExploreGrammar.g:84:17: LIST
            {
            LIST47=(Token)match(input,LIST,FOLLOW_LIST_in_list_command620);  
            stream_LIST.add(LIST47);



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
            // 84:23: -> ^( LIST )
            {
                // ExploreGrammar.g:84:26: ^( LIST )
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
    // ExploreGrammar.g:85:1: pretty_print_command : PRETTYPRINT -> ^( PRETTYPRINT ) ;
    public final ExploreGrammarParser.pretty_print_command_return pretty_print_command() throws RecognitionException {
        ExploreGrammarParser.pretty_print_command_return retval = new ExploreGrammarParser.pretty_print_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PRETTYPRINT48=null;

        Object PRETTYPRINT48_tree=null;
        RewriteRuleTokenStream stream_PRETTYPRINT=new RewriteRuleTokenStream(adaptor,"token PRETTYPRINT");

        try {
            // ExploreGrammar.g:85:22: ( PRETTYPRINT -> ^( PRETTYPRINT ) )
            // ExploreGrammar.g:85:25: PRETTYPRINT
            {
            PRETTYPRINT48=(Token)match(input,PRETTYPRINT,FOLLOW_PRETTYPRINT_in_pretty_print_command636);  
            stream_PRETTYPRINT.add(PRETTYPRINT48);



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
            // 85:37: -> ^( PRETTYPRINT )
            {
                // ExploreGrammar.g:85:40: ^( PRETTYPRINT )
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
    // ExploreGrammar.g:86:1: size_command : SIZE -> ^( SIZE ) ;
    public final ExploreGrammarParser.size_command_return size_command() throws RecognitionException {
        ExploreGrammarParser.size_command_return retval = new ExploreGrammarParser.size_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SIZE49=null;

        Object SIZE49_tree=null;
        RewriteRuleTokenStream stream_SIZE=new RewriteRuleTokenStream(adaptor,"token SIZE");

        try {
            // ExploreGrammar.g:86:14: ( SIZE -> ^( SIZE ) )
            // ExploreGrammar.g:86:17: SIZE
            {
            SIZE49=(Token)match(input,SIZE,FOLLOW_SIZE_in_size_command651);  
            stream_SIZE.add(SIZE49);



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
            // 86:22: -> ^( SIZE )
            {
                // ExploreGrammar.g:86:25: ^( SIZE )
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

    public static class custom_command_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "custom_command"
    // ExploreGrammar.g:88:1: custom_command : KEYWORD ( WS value )* -> ^( KEYWORD ( value )* ) ;
    public final ExploreGrammarParser.custom_command_return custom_command() throws RecognitionException {
        ExploreGrammarParser.custom_command_return retval = new ExploreGrammarParser.custom_command_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KEYWORD50=null;
        Token WS51=null;
        ExploreGrammarParser.value_return value52 = null;


        Object KEYWORD50_tree=null;
        Object WS51_tree=null;
        RewriteRuleTokenStream stream_WS=new RewriteRuleTokenStream(adaptor,"token WS");
        RewriteRuleTokenStream stream_KEYWORD=new RewriteRuleTokenStream(adaptor,"token KEYWORD");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // ExploreGrammar.g:88:16: ( KEYWORD ( WS value )* -> ^( KEYWORD ( value )* ) )
            // ExploreGrammar.g:88:18: KEYWORD ( WS value )*
            {
            KEYWORD50=(Token)match(input,KEYWORD,FOLLOW_KEYWORD_in_custom_command667);  
            stream_KEYWORD.add(KEYWORD50);

            // ExploreGrammar.g:88:26: ( WS value )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==WS) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ExploreGrammar.g:88:27: WS value
            	    {
            	    WS51=(Token)match(input,WS,FOLLOW_WS_in_custom_command670);  
            	    stream_WS.add(WS51);

            	    pushFollow(FOLLOW_value_in_custom_command672);
            	    value52=value();

            	    state._fsp--;

            	    stream_value.add(value52.getTree());

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
            // 88:38: -> ^( KEYWORD ( value )* )
            {
                // ExploreGrammar.g:88:41: ^( KEYWORD ( value )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_KEYWORD.nextNode(), root_1);

                // ExploreGrammar.g:88:52: ( value )*
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
    // ExploreGrammar.g:90:1: eol : ( ( WS )? NEWLINE )+ ;
    public final ExploreGrammarParser.eol_return eol() throws RecognitionException {
        ExploreGrammarParser.eol_return retval = new ExploreGrammarParser.eol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS53=null;
        Token NEWLINE54=null;

        Object WS53_tree=null;
        Object NEWLINE54_tree=null;

        try {
            // ExploreGrammar.g:90:5: ( ( ( WS )? NEWLINE )+ )
            // ExploreGrammar.g:90:7: ( ( WS )? NEWLINE )+
            {
            root_0 = (Object)adaptor.nil();

            // ExploreGrammar.g:90:7: ( ( WS )? NEWLINE )+
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
            	    // ExploreGrammar.g:90:8: ( WS )? NEWLINE
            	    {
            	    // ExploreGrammar.g:90:8: ( WS )?
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==WS) ) {
            	        alt7=1;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // ExploreGrammar.g:90:8: WS
            	            {
            	            WS53=(Token)match(input,WS,FOLLOW_WS_in_eol695); 
            	            WS53_tree = (Object)adaptor.create(WS53);
            	            adaptor.addChild(root_0, WS53_tree);


            	            }
            	            break;

            	    }

            	    NEWLINE54=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eol698); 
            	    NEWLINE54_tree = (Object)adaptor.create(NEWLINE54);
            	    adaptor.addChild(root_0, NEWLINE54_tree);


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
    // ExploreGrammar.g:92:1: value : ( name | typed_literal );
    public final ExploreGrammarParser.value_return value() throws RecognitionException {
        ExploreGrammarParser.value_return retval = new ExploreGrammarParser.value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ExploreGrammarParser.name_return name55 = null;

        ExploreGrammarParser.typed_literal_return typed_literal56 = null;



        try {
            // ExploreGrammar.g:93:2: ( name | typed_literal )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==VARIABLE||(LA9_0>=PREFIXED && LA9_0<=IRI)) ) {
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
                    // ExploreGrammar.g:93:4: name
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_name_in_value711);
                    name55=name();

                    state._fsp--;

                    adaptor.addChild(root_0, name55.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:94:4: typed_literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_typed_literal_in_value716);
                    typed_literal56=typed_literal();

                    state._fsp--;

                    adaptor.addChild(root_0, typed_literal56.getTree());

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
    // ExploreGrammar.g:97:1: name : ( resource | VARIABLE );
    public final ExploreGrammarParser.name_return name() throws RecognitionException {
        ExploreGrammarParser.name_return retval = new ExploreGrammarParser.name_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VARIABLE58=null;
        ExploreGrammarParser.resource_return resource57 = null;


        Object VARIABLE58_tree=null;

        try {
            // ExploreGrammar.g:98:2: ( resource | VARIABLE )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=PREFIXED && LA10_0<=IRI)) ) {
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
                    // ExploreGrammar.g:98:4: resource
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_resource_in_name731);
                    resource57=resource();

                    state._fsp--;

                    adaptor.addChild(root_0, resource57.getTree());

                    }
                    break;
                case 2 :
                    // ExploreGrammar.g:99:4: VARIABLE
                    {
                    root_0 = (Object)adaptor.nil();

                    VARIABLE58=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_name736); 
                    VARIABLE58_tree = (Object)adaptor.create(VARIABLE58);
                    adaptor.addChild(root_0, VARIABLE58_tree);


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
    // ExploreGrammar.g:102:1: resource : ( PREFIXED | IRI );
    public final ExploreGrammarParser.resource_return resource() throws RecognitionException {
        ExploreGrammarParser.resource_return retval = new ExploreGrammarParser.resource_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set59=null;

        Object set59_tree=null;

        try {
            // ExploreGrammar.g:103:2: ( PREFIXED | IRI )
            // ExploreGrammar.g:
            {
            root_0 = (Object)adaptor.nil();

            set59=(Token)input.LT(1);
            if ( (input.LA(1)>=PREFIXED && input.LA(1)<=IRI) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set59));
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
    // ExploreGrammar.g:107:1: typed_literal : ( LITERAL -> ^( TYPEDLITERAL LITERAL BLANKTYPE ) | LITERAL TYPED resource -> ^( TYPEDLITERAL LITERAL resource ) );
    public final ExploreGrammarParser.typed_literal_return typed_literal() throws RecognitionException {
        ExploreGrammarParser.typed_literal_return retval = new ExploreGrammarParser.typed_literal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LITERAL60=null;
        Token LITERAL61=null;
        Token TYPED62=null;
        ExploreGrammarParser.resource_return resource63 = null;


        Object LITERAL60_tree=null;
        Object LITERAL61_tree=null;
        Object TYPED62_tree=null;
        RewriteRuleTokenStream stream_TYPED=new RewriteRuleTokenStream(adaptor,"token TYPED");
        RewriteRuleTokenStream stream_LITERAL=new RewriteRuleTokenStream(adaptor,"token LITERAL");
        RewriteRuleSubtreeStream stream_resource=new RewriteRuleSubtreeStream(adaptor,"rule resource");
        try {
            // ExploreGrammar.g:108:2: ( LITERAL -> ^( TYPEDLITERAL LITERAL BLANKTYPE ) | LITERAL TYPED resource -> ^( TYPEDLITERAL LITERAL resource ) )
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
                    // ExploreGrammar.g:108:4: LITERAL
                    {
                    LITERAL60=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_typed_literal770);  
                    stream_LITERAL.add(LITERAL60);



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
                    // 108:13: -> ^( TYPEDLITERAL LITERAL BLANKTYPE )
                    {
                        // ExploreGrammar.g:108:16: ^( TYPEDLITERAL LITERAL BLANKTYPE )
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
                    // ExploreGrammar.g:109:4: LITERAL TYPED resource
                    {
                    LITERAL61=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_typed_literal787);  
                    stream_LITERAL.add(LITERAL61);

                    TYPED62=(Token)match(input,TYPED,FOLLOW_TYPED_in_typed_literal789);  
                    stream_TYPED.add(TYPED62);

                    pushFollow(FOLLOW_resource_in_typed_literal791);
                    resource63=resource();

                    state._fsp--;

                    stream_resource.add(resource63.getTree());


                    // AST REWRITE
                    // elements: LITERAL, resource
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 109:27: -> ^( TYPEDLITERAL LITERAL resource )
                    {
                        // ExploreGrammar.g:109:30: ^( TYPEDLITERAL LITERAL resource )
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


 

    public static final BitSet FOLLOW_WS_in_program287 = new BitSet(new long[]{0x000000000101FFF0L});
    public static final BitSet FOLLOW_command_in_program290 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_program293 = new BitSet(new long[]{0x000000000101FFF0L});
    public static final BitSet FOLLOW_command_in_program295 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_program299 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_command_in_command317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_command_in_command322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_props_command_in_command327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_props_command_in_command332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forward_star_command_in_command337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverse_star_command_in_command342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eval_command_in_command347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_all_command_in_command352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_choose_command_in_command357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_command_in_command362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interpreter_command_in_command368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_command_in_interpreter_command383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pretty_print_command_in_interpreter_command389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_command_in_interpreter_command394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_size_command_in_interpreter_command399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARD_in_forward_command412 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_forward_command414 = new BitSet(new long[]{0x0000000190000000L});
    public static final BitSet FOLLOW_name_in_forward_command416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSE_in_reverse_command432 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_reverse_command434 = new BitSet(new long[]{0x0000000190000000L});
    public static final BitSet FOLLOW_name_in_reverse_command436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARDSTAR_in_forward_star_command453 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_forward_star_command455 = new BitSet(new long[]{0x0000000190000000L});
    public static final BitSet FOLLOW_name_in_forward_star_command457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSESTAR_in_reverse_star_command473 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_reverse_star_command475 = new BitSet(new long[]{0x0000000190000000L});
    public static final BitSet FOLLOW_name_in_reverse_star_command477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORWARDPROPS_in_forward_props_command494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REVERSEPROPS_in_reverse_props_command509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup_command525 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_lookup_command527 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_typed_literal_in_lookup_command529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EVAL_in_eval_command546 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_eval_command548 = new BitSet(new long[]{0x0000000190000000L});
    public static final BitSet FOLLOW_name_in_eval_command550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_all_command566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHOOSE_in_choose_command580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFINE_in_define_command598 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_WS_in_define_command600 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_VARIABLE_in_define_command602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list_command620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRETTYPRINT_in_pretty_print_command636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_size_command651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_in_custom_command667 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_WS_in_custom_command670 = new BitSet(new long[]{0x0000000390000000L});
    public static final BitSet FOLLOW_value_in_custom_command672 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_WS_in_eol695 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_NEWLINE_in_eol698 = new BitSet(new long[]{0x0000000048000002L});
    public static final BitSet FOLLOW_name_in_value711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typed_literal_in_value716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resource_in_name731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_name736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_resource0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_in_typed_literal770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_in_typed_literal787 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_TYPED_in_typed_literal789 = new BitSet(new long[]{0x0000000180000000L});
    public static final BitSet FOLLOW_resource_in_typed_literal791 = new BitSet(new long[]{0x0000000000000002L});

}