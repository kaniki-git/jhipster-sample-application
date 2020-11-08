package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabSerologieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabSerologie.class);
        TabSerologie tabSerologie1 = new TabSerologie();
        tabSerologie1.setId(1L);
        TabSerologie tabSerologie2 = new TabSerologie();
        tabSerologie2.setId(tabSerologie1.getId());
        assertThat(tabSerologie1).isEqualTo(tabSerologie2);
        tabSerologie2.setId(2L);
        assertThat(tabSerologie1).isNotEqualTo(tabSerologie2);
        tabSerologie1.setId(null);
        assertThat(tabSerologie1).isNotEqualTo(tabSerologie2);
    }
}
