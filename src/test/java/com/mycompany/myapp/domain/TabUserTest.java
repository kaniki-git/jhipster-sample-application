package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabUser.class);
        TabUser tabUser1 = new TabUser();
        tabUser1.setId(1L);
        TabUser tabUser2 = new TabUser();
        tabUser2.setId(tabUser1.getId());
        assertThat(tabUser1).isEqualTo(tabUser2);
        tabUser2.setId(2L);
        assertThat(tabUser1).isNotEqualTo(tabUser2);
        tabUser1.setId(null);
        assertThat(tabUser1).isNotEqualTo(tabUser2);
    }
}
