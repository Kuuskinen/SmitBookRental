<template>
  <div class="col-md-12">
    <div class="card card-container">
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Username</label>
          <input v-model="user.username" v-validate="'required'" type="text" class="form-control" name="username"
                 id="username"/>
          <div v-if="errors.has('username')" class="alert alert-danger" role="alert">
            Kasutajanimi on kohustuslik
          </div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input v-model="user.password" v-validate="'required'" type="password" class="form-control"
                 name="password" id="password" />
          <div v-if="errors.has('password')" class="alert alert-danger" role="alert">
            Parool on kohustuslik
          </div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts">

import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
const Auth = namespace("Auth");

@Component
export default class Login extends Vue {
  private user: any = { username: "", password: "" };
  private loading: boolean = false;
  private message: string = "";

  @Auth.Getter
  private isLoggedIn!: boolean;

  @Auth.Action
  private login!: (data: any) => Promise<any>;

  created() {
    if (this.isLoggedIn) {
      this.$router.push("/profile");
    }
  }

  handleLogin() {
    this.loading = true;
    this.$validator.validateAll().then((isValid) => {
      if (!isValid) {
        this.loading = false;
        return;
      }

      if (this.user.username && this.user.password) {
        this.login(this.user).then(
            (data) => {
              this.$router.push("/profile");
            },
            (error) => {
              this.loading = false;
              this.message = error;
            }
        );
      }
    });
  }
}


/*
export default {
  name: "Login"

}
 */
</script>

<style scoped>

</style>
