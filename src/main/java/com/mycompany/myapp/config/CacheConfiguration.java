package com.mycompany.myapp.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.TabPatient.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabPatient.class.getName() + ".tabComptabilites");
            createCache(cm, com.mycompany.myapp.domain.TabAdministratif.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabAdministratif.class.getName() + ".tabUrgences");
            createCache(cm, com.mycompany.myapp.domain.TabCoordonnee.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabPersonnel.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabPersonnel.class.getName() + ".tabHospitals");
            createCache(cm, com.mycompany.myapp.domain.TabPersonnel.class.getName() + ".tabConsultations");
            createCache(cm, com.mycompany.myapp.domain.TabUser.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabRendezvous.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabSpecialite.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabHistoriquePatient.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabProfil.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabUserProfil.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabConsultation.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabConsultation.class.getName() + ".tabParamVitauxes");
            createCache(cm, com.mycompany.myapp.domain.TabConsultation.class.getName() + ".tabBiologies");
            createCache(cm, com.mycompany.myapp.domain.TabConsultation.class.getName() + ".tabUrgences");
            createCache(cm, com.mycompany.myapp.domain.TabConsultation.class.getName() + ".tabExamPhys");
            createCache(cm, com.mycompany.myapp.domain.TabConsultation.class.getName() + ".tabPersonnels");
            createCache(cm, com.mycompany.myapp.domain.TabParamVitaux.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabBiologie.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabConsObst.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabUrgence.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabHospital.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabHospital.class.getName() + ".tabConsultations");
            createCache(cm, com.mycompany.myapp.domain.TabHospital.class.getName() + ".tabPersonnels");
            createCache(cm, com.mycompany.myapp.domain.TabGynecologie.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabGynecologie.class.getName() + ".tabParamVitauxes");
            createCache(cm, com.mycompany.myapp.domain.TabGynecologie.class.getName() + ".tabBiologies");
            createCache(cm, com.mycompany.myapp.domain.TabGynecologie.class.getName() + ".tabConsObsts");
            createCache(cm, com.mycompany.myapp.domain.TabAccouchement.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabAccouchement.class.getName() + ".tabGynecologies");
            createCache(cm, com.mycompany.myapp.domain.TabExamPhys.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TabComptabilite.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
