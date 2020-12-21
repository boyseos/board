<template>
  <div class="home">
    <board/>
  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/views/board/Board.vue'

export default {
  name: 'Home',
  components: {
    Board
  }
}
</script>
