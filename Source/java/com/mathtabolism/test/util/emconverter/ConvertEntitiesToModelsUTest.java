/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathtabolism.entity.account.Account;
import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class ConvertEntitiesToModelsUTest extends BaseEMConverterUTest {

  @Test
  public void testNullAccountListToAccountModels() {
    List<Account> nullList = null;
    List<AccountModel> extracted = converter.convertEntitiesToModels(nullList);
    List<AccountModel> expected = null;
    assertThat(extracted, is(expected));
  }
  
  @Test
  public void testEmptyAccountListToAccountModels() {
    List<Account> nullList = new ArrayList<>();
    List<AccountModel> extracted = converter.convertEntitiesToModels(nullList);
    List<AccountModel> expected = new ArrayList<>();
    assertThat(extracted, is(expected));
  }
}
