<template>
  <div>
      <div v-for="dd in 10" :key="dd">{{dd}}</div>
  </div>
</template>

<script>
export default {
    data(){
        return {
            dd: 10
        }
    }
}
</script>

<style>

</style>