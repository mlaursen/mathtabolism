/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.mathtabolism.entity.account.Account;
import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class ExtractEntitiesFromModelsUTest extends BaseEMConverterUTest {

  @Test
  public void testNullAccountModelListExtractAccount() {
    List<AccountModel> nullList = null;
    List<Account> converted = converter.extractEntitiesFromModels(nullList);
    List<Account> expected = null;
    assertThat(converted, is(expected));
  }
  
  @Test
  public void testEmptyAccountModelListExtractAccount() {
    List<AccountModel> emptyList = new ArrayList<>();
    List<Account> converted = converter.extractEntitiesFromModels(emptyList);
    List<Account> expected = new ArrayList<>();
    assertThat(converted, is(expected));
  }

}
