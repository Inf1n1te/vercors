package vct.col.util;

import vct.col.ast.ProgramUnit;

/**
 * This class implements type checking of object oriented programs
 * that may use inheritance and/or overloading.
 * 
 * @author Stefan Blom
 *
 */
public class JavaTypeCheck extends AbstractTypeCheck {

  public JavaTypeCheck(ProgramUnit arg) {
    super(arg);
  }

}
