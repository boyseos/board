<template>
  <div class="home">
    <Board/>
  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/views/Board.vue'

export default {
  name: 'Home',
  components: {
    Board
  }
}
</script>
