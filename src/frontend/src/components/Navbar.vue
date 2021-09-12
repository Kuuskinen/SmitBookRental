<template>
  <div>
    <router-link to="/">Esileht</router-link>
    <router-link to="/books"> Raamatud laenutamiseks</router-link>
    <router-link to="/books_out"> Minu (O) raamatud</router-link>
    <router-link to="/my_books"> Minu (L) raamatud</router-link>
    <router-link to="/add_book"> Lisa uus raamat</router-link>
  </div>
</template>

<script>
export default {
  name: "Navbar"
}
</script>

<style scoped>

</style>
