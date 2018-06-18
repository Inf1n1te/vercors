package vct.col.rewrite;

import hre.ast.MessageOrigin;
import vct.col.ast.*;

public class SilverImplementIdentity extends AbstractRewriter {

  public SilverImplementIdentity(ProgramUnit source) {
    super(source);
    create.enter();
    create.setOrigin(new MessageOrigin("identity implementation"));
    Type int_t=create.primitive_type(PrimitiveSort.Integer);
    Method id=create.function_decl(int_t,null,"id",
        new DeclarationStatement[]{create.field_decl("x",int_t)}, create.local_name("x"));
    create.leave();
    target().add(id);
  }
  
  private String name=null;
  
  public void visit(BindingExpression e){
    if (e.binder == Binder.Star) {
      name = e.getDeclaration(0).name();
      super.visit(e);
      name=null;
    } else {
      super.visit(e);
    }
  }
  
  private boolean in_perm=false;
  
  public void visit(OperatorExpression e){
    switch(e.operator()){
    case Identity:
      result=create.invokation(null, null, "id", rewrite(e.arg(0)));
      break;
    case Subscript:
      if (e.arg(1) instanceof NameExpression){
        result=copy_rw.rewrite(e);
      } else {
        super.visit(e);
      }
      break;
    case Perm:
      in_perm=true;
      super.visit(e);
      in_perm=false;
      break;
    default:
      super.visit(e);
    }
  }

  public void visit(NameExpression e){
    super.visit(e);
    if (in_perm && e.getName().equals(name)){
      result=create.invokation(null, null, "id", result);
    }
  }
}
