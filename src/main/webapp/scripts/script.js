$(document).ready(function() {
    $.fn.animate_Text = function(callback) {
        var string = this.text();
        this.html(string.replace(/./g, '<span class="new">$&</span>'));
        var spans = this.find('span.new');
        var totalSpans = spans.length;
        spans.each(function(i, el) {
            setTimeout(function() {
                $(el).addClass('div_opacity');
                if (i === totalSpans - 1 && callback) {
                    callback();
                }
            }, 20 * i);
        });
    };

    function animate_Paragraphs(index) {
        if (index < $('.appearance').length) {
            $($('.appearance')[index]).show().animate_Text(function() {
                animate_Paragraphs(index + 1);
            });
        }
    }

    animate_Paragraphs(0);
});


